import { useState, useEffect } from 'react';

const useUserFeed = (username) => {
  const [userFeed, setUserFeed] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchUserFeed = async () => {
      setLoading(true);
      try {
        const response = await fetch(`/api/user_information/user_feed`, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ username }),
        });
        if (!response.ok) throw new Error(`Failed to fetch user feed`);
        const result = await response.json();
        setUserFeed(result);
      } catch (error) {
        console.error(`Error fetching user feed:`, error);
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchUserFeed();
  }, [username]);

  return { userFeed, loading, error };
};

export default useUserFeed;