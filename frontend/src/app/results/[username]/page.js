'use client';

import React, { useState, useEffect, useRef } from 'react';
import { useParams } from 'next/navigation';
import { User, Image as LucideImage, MessageCircle, Heart, Link as LinkIcon, Camera, Lock, ArrowUpDown, ArrowRight } from 'lucide-react';
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
  const dataFetchedRef = useRef(false);
  const [filteredPosts, setFilteredPosts] = useState([]);
  const [filterCriteria, setFilterCriteria] = useState('likes');
  const [sortOrder, setSortOrder] = useState('desc');
  const [analysisType, setAnalysisType] = useState('simple');

  const fetchData = async (type) => {
    try {
      setLoading(true);
      setError(null);

      const response = await fetch(`/api/analyze`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, analysis_type: type }),
      });

      if (!response.ok) throw new Error('Failed to fetch data');

      const result = await response.json();
      setData(result);
      setFilteredPosts(result.user_feed);
      console.log("user feed: ", result.user_feed)
      console.log("stories: ", result.stories);
      console.log("user info: ", result.user_info);
    } catch (error) {
      console.error('Error fetching data:', error);
      setError(error.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    if (dataFetchedRef.current) return;
    dataFetchedRef.current = true;
    fetchData(analysisType);
  }, [username]);

  const handleDetailedAnalysis = () => {
    setAnalysisType('detailed');
    fetchData('detailed');
  };

  useEffect(() => {
    if (data && data.user_feed) {
      filterAndSortPosts(data.user_feed);
    }
  }, [data, filterCriteria, sortOrder]);

  const filterAndSortPosts = (posts) => {
    let sorted = [...posts];
    sorted.sort((a, b) => {
      if (filterCriteria === 'date') {
        return sortOrder === 'desc'
          ? new Date(b.taken_at) - new Date(a.taken_at)
          : new Date(a.taken_at) - new Date(b.taken_at);
      } else if (filterCriteria === 'likes') {
        return sortOrder === 'desc'
          ? b.like_count - a.like_count
          : a.like_count - b.like_count;
      } else if (filterCriteria === 'comments') {
        return sortOrder === 'desc'
          ? b.comment_count - a.comment_count
          : a.comment_count - b.comment_count;
      }
    });
    setFilteredPosts(sorted.slice(0, 6));
  };

  if (loading) return <LoadingAnimation />;
  if (error) return <div className="text-center text-red-500">{error}</div>;
  if (!data) return <div className="text-center">Veri bulunamadı.</div>;

  const { user_info, stories } = data;
  console.log("user_info: ", user_info)
  console.log("stories: ", stories)
  return (
    <div className="min-h-screen bg-gradient-to-br from-indigo-900 via-purple-900 to-pink-700 text-white py-8 px-4">
      <div className="max-w-6xl mx-auto">
        <ProfileHeader user={user_info} />
        {user_info.is_private ? (
          <PrivateAccountMessage username={user_info.username} />
        ) : (
          <>
            <StoriesSection stories={stories} />
            {analysisType === 'simple' && (
              <DetailedAnalysisPromo handleDetailedAnalysis={handleDetailedAnalysis} />
            )}
            <FilterControls
              filterCriteria={filterCriteria}
              setFilterCriteria={setFilterCriteria}
              sortOrder={sortOrder}
              setSortOrder={setSortOrder}
            />
            <TopPostsSection feed={filteredPosts} />
          </>
        )}
      </div>
    </div>
  );
};

const FilterControls = ({ filterCriteria, setFilterCriteria, sortOrder, setSortOrder }) => (
  <motion.div
    initial={{ opacity: 0, y: 20 }}
    animate={{ opacity: 1, y: 0 }}
    transition={{ duration: 0.5 }}
    className="mb-6 flex justify-between items-center bg-white bg-opacity-10 backdrop-blur-lg rounded-xl p-4"
  >
    <select
      value={filterCriteria}
      onChange={(e) => setFilterCriteria(e.target.value)}
      className="bg-gray-800 text-white rounded p-2"
    >
      <option value="likes">Beğeni Sayısı</option>
      <option value="date">Tarih</option>
      <option value="comments">Yorum Sayısı</option>
    </select>
    <button
      onClick={() => setSortOrder(sortOrder === 'desc' ? 'asc' : 'desc')}
      className="flex items-center bg-pink-500 text-white rounded p-2 hover:bg-pink-600 transition-colors duration-200"
    >
      <ArrowUpDown className="mr-2" size={16} />
      {sortOrder === 'desc' ? 'Azalan' : 'Artan'}
    </button>
  </motion.div>
);

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

const formatNumber = (num) => {
  if (num >= 1000000) {
    return (num / 1000000).toFixed(1) + 'M';
  } else if (num >= 1000) {
    return (num / 1000).toFixed(1) + 'K';
  } else {
    return num.toString();
  }
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
      <StatItem label="Takipçiler" value={formatNumber(user.follower_count)} />
      <StatItem label="Takip" value={formatNumber(user.following_count)} />
    </div>
  </motion.div>
);

const StatItem = ({ label, value }) => (
  <div className="text-center">
    <p className="text-3xl font-bold">{value}</p>
    <p className="text-sm text-gray-300">{label}</p>
  </div>
);

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

const PrivateAccountMessage = ({ username }) => (
  <motion.div
    initial={{ opacity: 0, y: 50 }}
    animate={{ opacity: 1, y: 0 }}
    transition={{ duration: 0.5 }}
    className="bg-white bg-opacity-10 backdrop-blur-lg rounded-3xl p-8 shadow-2xl mb-8 text-center"
  >
    <Lock size={48} className="mx-auto mb-4 text-pink-500" />
    <h2 className="text-2xl font-bold mb-2">Gizli Hesap</h2>
    <p className="text-gray-300">
      @{username} kullanıcısının hesabı gizli olduğu için daha fazla veri çekilemiyor.
    </p>
    <p className="mt-4 text-sm text-gray-400">
      Gizli hesapların içeriği sadece onaylanmış takipçiler tarafından görüntülenebilir.
    </p>
  </motion.div>
);

const DetailedAnalysisPromo = ({ handleDetailedAnalysis }) => (
  <motion.div
    initial={{ opacity: 0, y: 50 }}
    animate={{ opacity: 1, y: 0 }}
    transition={{ duration: 0.5 }}
    className="bg-gradient-to-r from-pink-500 to-purple-600 rounded-lg shadow-lg p-6 my-8 text-white"
  >
    <h2 className="text-2xl font-bold mb-4">Detaylı Analiz ile Profilinizi Derinlemesine Keşfedin!</h2>
    <p className="mb-6">Tüm gönderilerinizin analizi, etkileşim oranları, en iyi performans gösteren içerikler ve daha fazlası...</p>
    <div className="flex justify-center">
      <motion.button
        onClick={handleDetailedAnalysis}
        className="bg-white text-purple-600 font-bold py-3 px-8 rounded-full hover:bg-purple-100 transition duration-300 ease-in-out transform hover:scale-105 focus:outline-none focus:ring-2 focus:ring-purple-500 focus:ring-opacity-50"
        whileHover={{ scale: 1.05 }}
        whileTap={{ scale: 0.95 }}
      >
        Detaylı Analizi Başlat
        <ArrowRight className="inline-block ml-2" />
      </motion.button>
    </div>
  </motion.div>
);

export default ResultsPage;
