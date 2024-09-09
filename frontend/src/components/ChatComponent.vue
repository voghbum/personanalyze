<template>
  <div>
    <!-- Kullanıcı adı girişi -->
    <v-text-field
      v-model="username"
      label="Kullanıcı Adı"
      outlined
    />

    <!-- Gönder butonu -->
    <v-btn color="primary" :disabled="!username || loading" @click="fetchData">Gönder</v-btn>

    <!-- Yükleniyor barı, buton ile araya boşluk eklemek için class ekliyoruz -->
    <v-progress-linear
      v-if="loading"
      class="loading-bar"
      color="primary"
      indeterminate
    />

    <!-- Yanıt varsa aiResult ve imageUrls'i göster, yoksa hata mesajı göster -->
    <v-card v-if="response && response.aiResult && !loading">
      <v-card-text>{{ response.aiResult }}</v-card-text>

      <!-- Resimleri göstermek için v-img componenti -->
      <div class="image-container">
        <v-img
          v-for="(url, index) in response.imageUrls"
          :key="index"
          class="image"
          contain
          max-width="300"
          :src="`http://localhost:8081/proxy-image?url=${encodeURIComponent(url)}`"
        />
      </div>
    </v-card>

    <v-alert v-else-if="!loading && !response" type="error">
      Veri yüklenemedi veya yanıt boş.
    </v-alert>
  </div>
</template>

<script lang="ts">
  import axios from 'axios'
  import { defineComponent, ref } from 'vue'
  import { ApiResponse } from '@/types/ApiResponse'

  export default defineComponent({
    setup () {
      const username = ref('')
      const response = ref<ApiResponse | null>(null)
      const loading = ref(false) // Yükleniyor durumu

      async function fetchData () {
        if (!username.value) {
          console.error('Kullanıcı adı girilmedi')
          return
        }
        loading.value = true // Yükleniyor durumu başlıyor
        try {
          const res = await axios.get(`http://localhost:8081/v1/api/roast?username=${username.value}`)
          response.value = res.data
        } catch (error) {
          console.error('API fetch error:', error)
        } finally {
          loading.value = false // Yükleniyor durumu bitiyor
        }
      }

      return {
        username,
        response,
        loading, // Loading state
        fetchData,
      }
    },
  })
</script>

<style scoped>
.loading-bar {
  margin-top: 10px;
}

.image {
  margin-top: 10px;
  max-width: 100%;
  border-radius: 8px; /* Resim köşelerini yuvarlatma */
}

.image-container {
  display: flex;
  justify-content: center; /* Yatay ortalama */
  align-items: center;    /* Dikey ortalama */
  flex-wrap: wrap;        /* Resimleri bir satıra sığmazsa alt alta yazdır */
  gap: 10px;              /* Resimler arasında boşluk */
  margin-top: 10px;
}

</style>
