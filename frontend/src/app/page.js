import React from 'react';
import dynamic from 'next/dynamic';

const InstagramAnalyzer = dynamic(() => import('../components/InstagramAnalyzer'), {
  loading: () => <p>Loading...</p>,
  ssr: false
});

export default function Home() {
  return (
    <main className="min-h-screen bg-gradient-to-br from-gray-900 to-gray-800 flex items-center justify-center p-4">
      <InstagramAnalyzer />
    </main>
  );
}