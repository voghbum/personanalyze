import { useState, useEffect, useRef } from 'react';

const useUserData = (username) => {
  const [userInfo, setUserInfo] = useState(null);
  const [userFeed, setUserFeed] = useState(null);
  const [stories, setStories] = useState(null);
  const [roastData, setRoastData] = useState(null);
  const [loading, setLoading] = useState({
    userInfo: true,
    userFeed: true,
    stories: true,
    roast: true,
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

    fetchData('user_info', setUserInfo, 'userInfo');
    fetchData('user_feed', setUserFeed, 'userFeed');
    fetchData('user_stories', setStories, 'stories');
    fetchData('roast', setRoastData, 'roast');
  }, [username]);

  return { userInfo, userFeed, stories, roastData, loading, error };
};

export default useUserData;