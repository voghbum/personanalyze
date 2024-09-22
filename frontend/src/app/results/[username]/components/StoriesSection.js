import React from 'react';
import Image from 'next/image';
import { motion } from 'framer-motion';

const StoriesSection = ({ stories }) => {
    if (!stories || !stories['Highlighted Stories'] || stories['Highlighted Stories'].length === 0) return null;

    return (
        <motion.div
            initial={{ opacity: 0, y: 50 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.5, delay: 0.2 }}
            className="mb-8"
        >
            <h2 className="text-2xl font-bold mb-4 text-pink-300">Öne Çıkan Hikayeler</h2>
            <div className="flex overflow-x-auto space-x-4 pb-4">
                {stories['Highlighted Stories'].map((story, index) => (
                    <div key={index} className="flex-shrink-0 w-24">
                        <div className="w-20 h-20 rounded-full border-2 border-pink-500 p-1">
                            <Image
                                src={story.cover_image_url}
                                alt={story.title}
                                width={80}
                                height={80}
                                className="rounded-full"
                            />
                        </div>
                        <p className="text-center text-sm mt-2 text-gray-300">{story.title}</p>
                    </div>
                ))}
            </div>
        </motion.div>
    );
};

export default StoriesSection;
