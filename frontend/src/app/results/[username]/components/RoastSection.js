import React, { useState } from 'react';
import { motion } from 'framer-motion';

const RoastSection = ({ roastData }) => {
  const [showDetailedAnalysis, setShowDetailedAnalysis] = useState(false);

  const handleDetailedAnalysis = () => {
    setShowDetailedAnalysis(true);
    // Burada detaylı analiz için ek bir API çağrısı yapabilirsiniz
  };

  return (
    <motion.div
      initial={{ opacity: 0, y: 20 }}
      animate={{ opacity: 1, y: 0 }}
      transition={{ duration: 0.5 }}
      className="bg-gradient-to-r from-purple-600 to-pink-600 rounded-lg p-6 mb-8 shadow-lg"
    >
      <h2 className="text-2xl font-bold mb-4 text-white">AI Roast</h2>
      <p className="text-lg mb-6 text-white">{roastData.ai_result}</p>
      {!showDetailedAnalysis && (
        <motion.button
          whileHover={{ scale: 1.05 }}
          whileTap={{ scale: 0.95 }}
          onClick={handleDetailedAnalysis}
          className="bg-white text-purple-600 font-bold py-2 px-4 rounded-full shadow-md hover:bg-purple-100 transition duration-300"
        >
          Detaylı Analiz Yap
        </motion.button>
      )}
      {showDetailedAnalysis && (
        <motion.div
          initial={{ opacity: 0, height: 0 }}
          animate={{ opacity: 1, height: 'auto' }}
          transition={{ duration: 0.5 }}
          className="mt-4 bg-white bg-opacity-20 p-4 rounded-lg"
        >
          <p className="text-white">Detaylı analiz burada gösterilecek...</p>
        </motion.div>
      )}
    </motion.div>
  );
};

export default RoastSection;