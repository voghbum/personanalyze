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
      setLoading(prev => ({ ...prev, [loadingKey]: true })); // Yükleme durumunu başlat
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
        setLoading(prev => ({ ...prev, [loadingKey]: false })); // Yükleme durumunu bitir
      }
    };

    const fetchProfileInfo = async () => {
      setLoading(prev => ({ ...prev, userInfo: true })); // Yükleme durumunu başlat
      try {
        const response = await fetch(`/api/user_information/profile_info`, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ username }),
        });
        if (response.status === 404) {
          setError("Bu kullanıcı adında bir profil yoktur"); // Handle 404 error
          return null; // Return null to indicate no profile found
        }
        if (!response.ok) throw new Error(`Failed to fetch profile info`);
        const result = await response.json();
        setUserInfo(result);
        return result; // Return the result for further checks
      } catch (error) {
        console.error(`Error fetching profile info:`, error);
        setError(error.message);
      } finally {
        setLoading(prev => ({ ...prev, userInfo: false })); // Yükleme durumunu bitir
      }
    };

    fetchProfileInfo().then(profileInfo => {
      if (profileInfo.is_private) {
        setLoading(prev => ({ ...prev, userFeed: false, stories: false, roast: false, ship: false }));
        return; // Skip other fetches if profile is private
      }
      // Fetch other data only if profile is public
      fetchData('user_information/user_feed', setUserFeed, 'userFeed');
      fetchData('user_information/user_stories', setStories, 'stories');
      fetchData('ai/personal_life', setRoastData, 'roast');
      fetchData('ai/love_life', setShipData, 'ship');
    });
  }, [username]);

  return { userInfo, userFeed, stories, roastData, shipData, loading, error };
};

export default useUserData;
