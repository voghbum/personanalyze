# 1. Aşama: Maven ile projeyi build etme aşaması
FROM maven:3.8.5-openjdk-17 AS build

# Proje dosyalarını Docker imajına kopyala
COPY . /usr/src/app

# Çalışma dizinine geç
WORKDIR /usr/src/app

# Maven ile projeyi build et (modüller dahil)
RUN mvn clean install -DskipTests

# 2. Aşama: Sadece JDK kullanarak jar dosyasını çalıştırma aşaması
FROM openjdk:17-jdk-slim

# Uygulama için gerekli portu expose edelim
EXPOSE 8081

# Build aşamasında oluşan jar dosyasını imaja kopyala
COPY --from=build /usr/src/app/application/target/application-*.jar /usr/app/application.jar

# Uygulamanın çalıştırılması
ENTRYPOINT ["java", "-jar", "/usr/app/application.jar"]
