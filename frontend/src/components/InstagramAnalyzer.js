'use client';

import React, { useState, useEffect } from 'react';
import { useRouter } from 'next/navigation';
import { Instagram, HelpCircle } from 'lucide-react'; //icon kullanımı
import { motion, AnimatePresence } from 'framer-motion'; // animasyyonlar ve geçişler

const InstagramAnalyzer = () => {
  const [username, setUsername] = useState('');
  const [showTip, setShowTip] = useState(false);
  const router = useRouter();

  useEffect(() => {
    const timer = setTimeout(() => setShowTip(true), 1000); // 3 saniye sonra showtip true olur ve tip kullanıcıya gösterilir. Burada 3 saniye alınıyor.
    return () => clearTimeout(timer);
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();
    router.push(`/results/${username}`);
  };

  return (
    <div className="w-full max-w-md space-y-8">
      <motion.div 
        initial={{ opacity: 0, y: -50 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5 }}
        className="text-center space-y-2"
      >
        <div className="w-24 h-24 mx-auto bg-gradient-to-tr from-yellow-400 via-red-500 to-purple-600 rounded-2xl p-1">
          <div className="flex items-center justify-center w-full h-full bg-gray-900 rounded-xl">
            <Instagram className="h-12 w-12 text-white" />
          </div>
        </div>
        <h1 className="text-4xl font-bold text-transparent bg-clip-text bg-gradient-to-r from-purple-400 via-pink-500 to-yellow-500">
          Instagram Profil Analizi
        </h1>
        <p className="text-gray-300 text-xl">Analiz için kullanıcı adını girin</p>
      </motion.div>

      <motion.form 
        onSubmit={handleSubmit}
        className="mt-8 space-y-6"
        initial={{ opacity: 0, scale: 0.8 }}
        animate={{ opacity: 1, scale: 1 }}
        transition={{ delay: 0.3, duration: 0.5 }}
      >
        <div className="rounded-md shadow-sm -space-y-px">
          <input
            type="text"
            placeholder="Instagram kullanıcı adı"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            className="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-700 placeholder-gray-500 text-white rounded-t-md focus:outline-none focus:ring-pink-500 focus:border-pink-500 focus:z-10 sm:text-sm bg-gray-800"
            required
          />
        </div>
        <div>
          <button 
            type="submit"
            className="group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-gradient-to-r from-purple-500 via-pink-500 to-yellow-500 hover:from-purple-600 hover:via-pink-600 hover:to-yellow-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-pink-500 transition-all duration-200"
          >
            Analiz Et
          </button>
        </div>
      </motion.form>

      <AnimatePresence>
        {showTip && (
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            exit={{ opacity: 0, y: -20 }}
            className="bg-gray-800 border-l-4 border-pink-500 text-gray-300 p-4 rounded mt-4"
            role="alert"
          >
            <div className="flex">
              <div className="flex-shrink-0">
                <HelpCircle className="h-5 w-5 text-pink-400" aria-hidden="true" />
              </div>
              <div className="ml-3">
                <p className="text-sm">
                  İpucu: Popüler bir influencer'ın kullanıcı adını deneyin!
                </p>
              </div>
            </div>
          </motion.div>
        )}
      </AnimatePresence>
    </div>
  );
};

export default InstagramAnalyzer;