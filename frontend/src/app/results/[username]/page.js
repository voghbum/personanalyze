'use client';

import React, { useState, useEffect } from 'react';
import { useParams } from 'next/navigation';
import useUserInfo from './hooks/useUserInfo';
import ProfileInfoSection from './components/ProfileInfoSection.js';
import RoastSection from './components/RoastSection';
import ShipSection from './components/ShipSection';
import ProfilePostsSection from './components/ProfilePostsSection';
import LoadingAnimation from './components/LoadingAnimation';
import PrivateAccountMessage from "./components/PrivateAccountMessage";
import DetailedAnalysisModal from './components/DetailedAnalysisModal';

const ResultsPage = () => {
    const { username } = useParams();
    const { userInfo, loading: loadingUserInfo, error } = useUserInfo(username);
    const [isModalOpen, setIsModalOpen] = useState(false);


    const [userFeed, setUserFeed] = useState(null);
    const [stories, setStories] = useState(null);
    const [roastData, setRoastData] = useState(null);
    const [shipData, setShipData] = useState(null);
    const [loadingUserFeed, setLoadingUserFeed] = useState(true);
    const [loadingStories, setLoadingStories] = useState(true);
    const [loadingRoast, setLoadingRoast] = useState(true);
    const [loadingShip, setLoadingShip] = useState(true);
    const [privateAnalysisData, setPrivateAnalysisData] = useState(null);
    const [loadingPrivateAnalysis, setLoadingPrivateAnalysis] = useState(true);

    useEffect(() => {
        if (userInfo && !userInfo.is_private) {
            // Fetch other data only if the account is not private
            const fetchData = async () => {
                // Fetch user feed
                const feedResponse = await fetch(`/api/user_information/user_feed`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ username }),
                });
                const feedResult = await feedResponse.json();
                setUserFeed(feedResult);
                setLoadingUserFeed(false);

                // Fetch user stories
                const storiesResponse = await fetch(`/api/user_information/user_stories`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ username }),
                });
                const storiesResult = await storiesResponse.json();
                setStories(storiesResult);
                setLoadingStories(false);

                // Fetch roast data
                const roastResponse = await fetch(`/api/ai/personal_life`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ username }),
                });
                const roastResult = await roastResponse.json();
                setRoastData(roastResult);
                setLoadingRoast(false);

                // Fetch ship data
                const shipResponse = await fetch(`/api/ai/love_life`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ username }),
                });
                const shipResult = await shipResponse.json();
                setShipData(shipResult);
                setLoadingShip(false);
            };

            fetchData();
        } else {
            const fetchPrivateAnalysis = async () => {
                const analysisResponse = await fetch(`/api/ai/analyze_for_private`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ username }),
                });
                const analysisResult = await analysisResponse.json();
                setPrivateAnalysisData(analysisResult);
                setLoadingPrivateAnalysis(false);
            };

            fetchPrivateAnalysis();
        }
    }, [userInfo, username]);

    if (error)
        return (
            <div className="min-h-screen bg-gradient-to-br from-indigo-900 via-purple-900 to-pink-700 text-white py-8 px-4">
                <div>
                    <p>Kullanıcı bulunamadı veya bir hata oluştu.</p> {/* Hata mesajı */}
                    <button
                        onClick={() => router.push('/')} // Ana sayfaya yönlendirme
                        className="mt-4 bg-blue-500 text-white py-2 px-4 rounded"
                    >
                        Ana sayfaya dön
                    </button>
                </div>
            </div>
        );

    return (
        <div className="min-h-screen bg-gradient-to-br from-indigo-900 via-purple-900 to-pink-700 text-white py-8 px-4">
            <div className="max-w-6xl mx-auto">
                {loadingUserInfo ? (
                    <LoadingAnimation />
                ) : (
                    <ProfileInfoSection userInfo={userInfo} />
                )}
                {userInfo && userInfo.is_private ? (
                    <>
                        {loadingPrivateAnalysis ? (
                            <LoadingAnimation />
                        ) : (
                            <RoastSection roastData={privateAnalysisData} /> // "Ben Kimim" component
                        )}
                        <PrivateAccountMessage username={userInfo.username} />
                    </>
                ) : (
                    <>
                        {loadingRoast ? (
                            <LoadingAnimation />
                        ) : (
                            <RoastSection roastData={roastData} />
                        )}
                        {loadingShip ? (
                            <LoadingAnimation />
                        ) : (
                            <ShipSection shipData={shipData} />
                        )}
                        <div className="text-center mt-8 mb-8">
                            <button
                                className="bg-gradient-to-r from-pink-500 to-purple-600 hover:from-pink-600 hover:to-purple-700 text-white font-bold py-3 px-6 rounded-full shadow-lg transform transition duration-300 hover:scale-105"
                                onClick={() => setIsModalOpen(true)}
                            >
                                Detaylı analizi göster
                            </button>
                        </div>
                        <ProfilePostsSection
                            stories={stories}
                            userFeed={userFeed}
                            loading={loadingUserFeed}
                            userInfo={userInfo}
                        />
                    </>
                )}
            </div>
            {!userInfo?.is_private && (
                <DetailedAnalysisModal
                    isOpen={isModalOpen}
                    onClose={() => setIsModalOpen(false)}
                    username={username}
                />
            )}
        </div>
    );
};

export default ResultsPage;
