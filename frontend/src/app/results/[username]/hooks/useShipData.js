import { useState, useEffect } from 'react';

const useShipData = (username) => {
  const [shipData, setShipData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchShipData = async () => {
      setLoading(true);
      try {
        const response = await fetch(`/api/ai/love_life`, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ username }),
        });
        if (!response.ok) throw new Error(`Failed to fetch ship data`);
        const result = await response.json();
        setShipData(result);
      } catch (error) {
        console.error(`Error fetching ship data:`, error);
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchShipData();
  }, [username]);

  return { shipData, loading, error };
};

export default useShipData;
