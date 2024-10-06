import { useState, useEffect } from 'react';

const useRoastData = (username) => {
  const [roastData, setRoastData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchRoastData = async () => {
      setLoading(true);
      try {
        const response = await fetch(`/api/ai/personal_life`, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ username }),
        });
        if (!response.ok) throw new Error(`Failed to fetch roast data`);
        const result = await response.json();
        setRoastData(result);
      } catch (error) {
        console.error(`Error fetching roast data:`, error);
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchRoastData();
  }, [username]);

  return { roastData, loading, error };
};

export default useRoastData;
