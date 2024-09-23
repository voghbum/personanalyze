import React from 'react';
import { motion } from 'framer-motion';

const ShipSection = ({ shipData }) => {
  if (!shipData) return null;

  return (
    <motion.div
      initial={{ opacity: 0, y: 50 }}
      animate={{ opacity: 1, y: 0 }}
      transition={{ duration: 0.5, delay: 0.3 }}
      className="mb-8 bg-gradient-to-r from-pink-600 to-purple-600 rounded-lg p-6 shadow-lg"
    >
      <h2 className="text-2xl font-bold mb-4 text-white">AI Eşleştirme</h2>
      <p className="text-lg text-white">{shipData.ai_result}</p>
    </motion.div>
  );
};

export default ShipSection;