import { useState, useEffect, useRef } from 'react';

const useUserInfo = (username) => {
  const [userInfo, setUserInfo] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const dataFetchedRef = useRef(false);

  useEffect(() => {
    if (dataFetchedRef.current) return;
    dataFetchedRef.current = true;

    const fetchUserInfo = async () => {
      setLoading(true);
      try {
        const response = await fetch(`/api/user_information/profile_info`, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ username }),
        });
        if (response.status === 404) {
          setError("Bu kullanıcı adında bir profil yoktur");
          return null;
        }
        if (!response.ok) throw new Error(`Failed to fetch profile info`);
        const result = await response.json();
        setUserInfo(result);
      } catch (error) {
        console.error(`Error fetching profile info:`, error);
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchUserInfo();
  }, [username]);

  return { userInfo, loading, error };
};

export default useUserInfo;
