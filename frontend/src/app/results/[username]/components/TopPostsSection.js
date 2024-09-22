import React from 'react';
import Image from 'next/image';
import { motion } from 'framer-motion';
import { MessageCircle, Heart } from 'lucide-react';



const TopPostsSection = ({ feed }) => {
    if (!feed || feed.length === 0) return null;

    return (
        <motion.div
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.5 }}
        >
            <h2 className="text-2xl font-bold mb-4 text-pink-300">Öne Çıkan Gönderiler</h2>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                {feed.map((post) => (
                    <PostCard key={post.id} post={post} />
                ))}
            </div>
        </motion.div>
    );
};

const PostCard = ({ post }) => (
    <motion.div
        initial={{ opacity: 0, scale: 0.9 }}
        animate={{ opacity: 1, scale: 1 }}
        transition={{ duration: 0.3 }}
        className="bg-white bg-opacity-10 backdrop-blur-lg rounded-xl overflow-hidden shadow-lg"
    >
        <div className="relative aspect-square">
            <Image
                src={post.thumbnail_url}
                alt={`Post ${post.id}`}
                layout="fill"
                objectFit="cover"
            />
        </div>
        <div className="p-4">
            <p className="text-sm mb-2 line-clamp-2 text-gray-200">{post.caption.text}</p>
            <div className="flex justify-between items-center mt-2 text-sm">
        <span className="flex items-center text-pink-400">
          <Heart className="mr-1" size={16} /> {post.like_count}
        </span>
                <span className="flex items-center text-blue-400">
          <MessageCircle className="mr-1" size={16} /> {post.comment_count}
        </span>
            </div>
        </div>
    </motion.div>
);

export default TopPostsSection;
