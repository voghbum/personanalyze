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
      <h2 className="text-2xl font-bold mb-4 text-white">Ben Kimim?</h2>
      <p className="text-lg mb-6 text-white">{roastData.ai_result}</p>
    </motion.div>
  );
};

export default RoastSection;
