import { useState, useEffect } from 'react';

const useUserStories = (username) => {
  const [stories, setStories] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchUserStories = async () => {
      setLoading(true);
      try {
        const response = await fetch(`/api/user_information/user_stories`, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ username }),
        });
        if (!response.ok) throw new Error(`Failed to fetch user stories`);
        const result = await response.json();
        setStories(result);
      } catch (error) {
        console.error(`Error fetching user stories:`, error);
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchUserStories();
  }, [username]);

  return { stories, loading, error };
};

export default useUserStories;
