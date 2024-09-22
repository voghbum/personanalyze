/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: false,
  images: {
    domains: ['instagram.com', 'www.instagram.com'],
    remotePatterns: [
      {
        protocol: 'https',
        hostname: '**.cdninstagram.com',
      },
      {
        protocol: 'https',
        hostname: 'scontent**.fbcdn.net',
      },
      {
        protocol: 'https',
        hostname: 'instagram**.fbcdn.net',
      },
    ],
  },
  async rewrites() {
    return [
      {
        source: '/api/:path*', //frontenddeki api isteklerini backenddeki api isteklerine yönlendirir.
        //components/instagarmAnalyzer.js içindeki fetch isteği bu şekilde çalışır.
        destination: 'http://localhost:8000/api/:path*',
      },
    ];
  },
};

module.exports = nextConfig;