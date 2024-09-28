'use client';

import React from 'react';
import { useParams } from 'next/navigation';
import useUserData from './hooks/useUserData';
import ProfileInfoSection from './components/ProfileInfoSection.js';
import RoastSection from './components/RoastSection';
import ShipSection from './components/ShipSection';
import ProfilePostsSection from './components/ProfilePostsSection';
import LoadingAnimation from './components/LoadingAnimation';
import PrivateAccountMessage from "./components/PrivateAccountMessage";

const ResultsPage = () => {
  const { username } = useParams();
  const { userInfo, userFeed, stories, roastData, shipData, loading, error } = useUserData(username);

  if (error) return <div className="text-center text-red-500">{error}</div>;

  return (
    <div className="min-h-screen bg-gradient-to-br from-indigo-900 via-purple-900 to-pink-700 text-white py-8 px-4">
      <div className="max-w-6xl mx-auto">
        {loading.userInfo ? (
          <LoadingAnimation />
        ) : (
          <ProfileInfoSection userInfo={userInfo} />
        )}
        {userInfo && userInfo.is_private ? (
          <PrivateAccountMessage username={userInfo.username} />
        ) : (
          <>
            {loading.roast ? (
              <LoadingAnimation />
            ) : (
              <RoastSection roastData={roastData} />
            )}
            {loading.ship ? (
              <LoadingAnimation />
            ) : (
              <ShipSection shipData={shipData} />
            )}
            <div className="text-center mt-8 mb-8">
              <button className="bg-gradient-to-r from-pink-500 to-purple-600 hover:from-pink-600 hover:to-purple-700 text-white font-bold py-3 px-6 rounded-full shadow-lg transform transition duration-300 hover:scale-105">
                Detaylı analizi göster
              </button>
            </div>
            <ProfilePostsSection
              stories={stories}
              userFeed={userFeed}
              loading={loading}
            />
          </>
        )}
      </div>
    </div>
  );
};

export default ResultsPage;
