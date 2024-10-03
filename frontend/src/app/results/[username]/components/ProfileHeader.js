import React from 'react';
import Image from 'next/image';
import { Link as LinkIcon, Camera } from 'lucide-react';
import { motion } from 'framer-motion';
import {formatNumber} from "chart.js/helpers";

const formatCount = (count) => {
    if (count >= 1000000) {
        return (count / 1000000).toFixed(1) + 'M'; // Milyon
    } else if (count >= 1000) {
        return (count / 1000).toFixed(1) + 'K'; // Bin
    }
    return count; // 1000'den az ise sayıyı olduğu gibi döndür
};

const ProfileHeader = ({ user }) => (
    <motion.div
        initial={{ opacity: 0, y: 50 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5 }}
        className="bg-white bg-opacity-10 backdrop-blur-lg rounded-3xl p-8 shadow-2xl mb-8"
    >
        <div className="flex flex-col md:flex-row items-center space-y-4 md:space-y-0 md:space-x-8">
            <div className="relative">
                <Image
                    src={user.profile_pic_url}
                    alt={user.username}
                    width={150}
                    height={150}
                    className="rounded-full border-4 border-pink-500"
                />
                <div className="absolute -bottom-2 -right-2 bg-green-500 rounded-full p-2">
                    <Camera size={24} />
                </div>
            </div>
            <div className="text-center md:text-left">
                <h1 className="text-4xl font-bold bg-clip-text text-transparent bg-gradient-to-r from-pink-500 to-purple-500">
                    {user.full_name}
                </h1>
                <p className="text-xl text-gray-300">@{user.username}</p>
                <p className="mt-2 text-gray-200">{user.biography}</p>
                {user.external_url && (
                    <a href={user.external_url} target="_blank" rel="noopener noreferrer"
                       className="mt-2 text-pink-400 hover:text-pink-300 flex items-center justify-center md:justify-start">
                        <LinkIcon size={16} className="mr-1" /> {user.external_url}
                    </a>
                )}
            </div>
        </div>
        <div className="flex justify-around mt-8">
            <StatItem label="Gönderiler" value={formatNumber(user.media_count)} />
            <StatItem label="Takipçiler" value={formatCount(user.follower_count)} /> {/* Güncellenmiş kısım */}
            <StatItem label="Takip" value={formatCount(user.following_count)} /> {/* Güncellenmiş kısım */}
        </div>
    </motion.div>
);

const StatItem = ({ label, value }) => (
    <div className="text-center">
        <p className="text-3xl font-bold">{value}</p>
        <p className="text-sm text-gray-300">{label}</p>
    </div>
);

export default ProfileHeader;
