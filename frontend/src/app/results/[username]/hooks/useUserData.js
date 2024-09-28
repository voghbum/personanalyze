import { useState, useEffect, useRef } from 'react';

const useUserData = (username) => {
  const [userInfo, setUserInfo] = useState(null);
  const [userFeed, setUserFeed] = useState(null);
  const [stories, setStories] = useState(null);
  const [roastData, setRoastData] = useState(null);
  const [shipData, setShipData] = useState(null);
  const [loading, setLoading] = useState({
    userInfo: true,
    userFeed: true,
    stories: true,
    roast: true,
    ship: true,
  });
  const [error, setError] = useState(null);
  const dataFetchedRef = useRef(false);

  useEffect(() => {
    if (dataFetchedRef.current) return;
    dataFetchedRef.current = true;

    const fetchData = async (endpoint, setter, loadingKey) => {
      try {
        const response = await fetch(`/api/${endpoint}`, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ username }),
        });
        if (!response.ok) throw new Error(`Failed to fetch ${endpoint}`);
        const result = await response.json();
        setter(result);
      } catch (error) {
        console.error(`Error fetching ${endpoint}:`, error);
        setError(error.message);
      } finally {
        setLoading(prev => ({ ...prev, [loadingKey]: false }));
      }
    };

    fetchData('user_information/profile_info', setUserInfo, 'userInfo');
    fetchData('user_information/user_feed', setUserFeed, 'userFeed');
    fetchData('user_information/user_stories', setStories, 'stories');
    fetchData('ai/personal_life', setRoastData, 'roast');
    fetchData('ai/love_life', setShipData, 'ship');
  }, [username]);

  return { userInfo, userFeed, stories, roastData, shipData, loading, error };
};

export default useUserData;
