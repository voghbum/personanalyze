'use client';

import React, { useEffect, useState } from 'react';
import { useParams } from 'next/navigation';
import { User, Users, Image as LucideImage, MessageCircle, Heart, ChevronDown, Link as LinkIcon, ChevronUp } from 'lucide-react';
import Image from 'next/image';
import { motion } from 'framer-motion';

const LoadingAnimation = () => (
  <div className="flex justify-center items-center h-screen">
    <div className="animate-spin rounded-full h-32 w-32 border-t-2 border-b-2 border-purple-500"></div>
  </div>
);

const ResultsPage = () => {
  const { username } = useParams();
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [expandedCaptions, setExpandedCaptions] = useState({});
  const [showDetailedAnalysis, setShowDetailedAnalysis] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const cachedData = localStorage.getItem(`instagramAnalysis_${username}`);
        if (cachedData) {
          setData(JSON.parse(cachedData));
          setLoading(false);
          return;
        }

        const response = await fetch(`/api/analyze`, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ username }),
        });
        if (!response.ok) throw new Error('Failed to fetch data');
        const result = await response.json();
        setData(result);
        localStorage.setItem(`instagramAnalysis_${username}`, JSON.stringify(result));
      } catch (error) {
        console.error('Error fetching data:', error);
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [username]);

  const toggleCaption = (index) => {
    setExpandedCaptions(prev => ({
      ...prev,
      [index]: !prev[index]
    }));
  };

  const formatBiography = (bio) => {
    return bio.split('\n').map((line, index) => (
      <React.Fragment key={index}>
        {line}
        <br />
      </React.Fragment>
    ));
  };

  if (loading) return <LoadingAnimation />;
  if (error) return <div className="text-red-500 text-center">Error: {error}</div>;
  if (!data || !data.user_profile) return <div className="text-white text-center">No data available</div>;

  const { user_profile, user_posts } = data;

  return (
    <div className="min-h-screen bg-gradient-to-br from-purple-900 via-blue-900 to-indigo-900 text-white p-8">
      <div className="max-w-6xl mx-auto">
        <motion.h1 
          initial={{ opacity: 0, y: -50 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.5 }}
          className="text-5xl font-bold text-center mb-12 text-transparent bg-clip-text bg-gradient-to-r from-pink-500 to-yellow-500"
        >
          Instagram Profil Analizi Sonuçları
        </motion.h1>
        
        <motion.div 
          initial={{ opacity: 0, scale: 0.9 }}
          animate={{ opacity: 1, scale: 1 }}
          transition={{ duration: 0.5 }}
          className="bg-gray-800 bg-opacity-50 backdrop-blur-lg rounded-lg p-8 shadow-2xl mb-12"
        >
          <div className="flex items-center mb-6">
            <Image 
              src={user_profile.hd_profile_pic_versions?.url || '/default-avatar.png'}
              alt={user_profile.username} 
              width={120} 
              height={120} 
              className="rounded-full mr-6 border-4 border-pink-500"
            />
            <div>
              <h2 className="text-3xl font-bold">{user_profile.full_name}</h2>
              <p className="text-xl text-pink-400">@{user_profile.about.username}</p>
              <div className="tflex justify-between mb-6">
                {formatBiography(user_profile.biography)}
                {user_profile.bio_links && user_profile.bio_links.length > 0 && user_profile.bio_links[0].url && (
                  <a 
                    href={user_profile.bio_links[0].url} 
                    target="_blank" 
                    rel="noopener noreferrer" 
                    className="text-blue-400 hover:underline flex items-center mt-2"
                  >
                    <LinkIcon size={16} className="mr-1" />
                    {user_profile.bio_links[0].url}
                  </a>
                )}
              </div>
            </div>
          </div>
          <div className="flex justify-between mt-6">
            <StatCard icon={<LucideImage />} value={user_profile.media_count} label="Gönderiler" />
            <StatCard icon={<Users />} value={user_profile.follower_count} label="Takipçiler" />
            <StatCard icon={<User />} value={user_profile.following_count} label="Takip" />
          </div>
        </motion.div>

        {!user_profile.is_private && user_posts && (
          <motion.div>
            <h3 className="text-3xl font-bold mb-6 text-pink-500">En Popüler Gönderiler</h3>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mb-12">
              {user_posts.items
                .sort((a, b) => b.like_count - a.like_count)
                .slice(0, 6)
                .map((post, index) => (
                  <PostCard key={index} post={post} index={index} expandedCaptions={expandedCaptions} toggleCaption={toggleCaption} />
                ))}
            </div>
          </motion.div>
        )}

        {user_profile.is_private && (
          <motion.div>
            <h3 className="text-3xl font-bold mb-6 text-pink-500 mt-12">Gizli Profil</h3>
            <p className="text-lg mb-4">Bu profil gizlidir. Gönderi bilgileri görüntülenemiyor.</p>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mb-12">
              {[...Array(6)].map((_, index) => (
                <PostCard key={index} post={{}} index={index} isPrivate={true} />
              ))}
            </div>
          </motion.div>
        )}

        <div className="text-center mb-12">
          <motion.button
            whileHover={{ scale: 1.05 }}
            whileTap={{ scale: 0.95 }}
            onClick={() => setShowDetailedAnalysis(!showDetailedAnalysis)}
            className="bg-gradient-to-r from-pink-500 to-yellow-500 text-white font-bold py-3 px-6 rounded-full text-lg shadow-lg transition duration-300 ease-in-out transform hover:-translate-y-1"
          >
            {showDetailedAnalysis ? 'Detaylı Analizi Gizle' : 'Detaylı Analizi Göster'}
            {showDetailedAnalysis ? <ChevronUp className="inline-block ml-2" /> : <ChevronDown className="inline-block ml-2" />}
          </motion.button>
        </div>

        {showDetailedAnalysis && (
          <motion.div 
            initial={{ opacity: 0, y: 50 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.5 }}
            className="bg-indigo-800 rounded-lg p-8 shadow-2xl mt-8"
          >
            <h3 className="text-3xl font-bold mb-6 text-pink-500">Detaylı Analiz</h3>
            <pre className="whitespace-pre-wrap text-sm">
              {JSON.stringify(data, null, 2)}
            </pre>
          </motion.div>
        )}
      </div>
    </div>
  );
};

const StatCard = ({ icon, value, label }) => (
  <motion.div 
    whileHover={{ scale: 1.05 }}
    className="text-center bg-gray-700 bg-opacity-30 rounded-lg p-4 flex flex-col items-center shadow-lg"
  >
    {React.cloneElement(icon, { className: "text-pink-400 mb-2", size: 24 })}
    <span className="text-2xl font-bold">{value}</span>
    <p className="text-gray-300">{label}</p>
  </motion.div>
);

const PostCard = ({ post, index, expandedCaptions, toggleCaption, isPrivate }) => (
  <motion.div 
    initial={{ opacity: 0, y: 50 }}
    animate={{ opacity: 1, y: 0 }}
    transition={{ duration: 0.5, delay: index * 0.1 }}
    className="bg-indigo-800 rounded-lg overflow-hidden shadow-lg"
  >
    <Image 
      src={isPrivate ? '/path/to/default/private_post_image.jpg' : post.thumbnail_url}
      alt={`Gönderi ${index + 1}`} 
      width={400} 
      height={400}
      className="w-full h-64 object-cover"
    />
    {!isPrivate ? (
      <div className="p-4">
        <div className={`text-sm mb-2 ${expandedCaptions[index] ? '' : 'h-12 overflow-hidden'}`}>
          {post.caption?.text}
        </div>
        {post.caption?.text && post.caption.text.length > 100 && (
          <button 
            onClick={() => toggleCaption(index)}
            className="text-pink-400 text-sm mb-2 hover:underline"
          >
            {expandedCaptions[index] ? 'Daha az göster' : 'Devamını oku'}
          </button>
        )}
        <div className="flex justify-between text-sm text-gray-400">
          <span>
            <Heart className="inline-block mr-1 text-pink-500 fill-current" size={16} /> {post.like_count}
          </span>
          <span>
            <MessageCircle className="inline-block mr-1 text-blue-400" size={16} /> {post.comment_count}
          </span>
        </div>
      </div>
    ) : (
      <div className="p-4 text-sm">Bu gönderi gizli bir profile aittir.</div>
    )}
  </motion.div>
);

export default ResultsPage;
