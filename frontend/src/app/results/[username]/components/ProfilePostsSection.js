import React, { useState, useEffect } from 'react';
import StoriesSection from './StoriesSection';
import FilterControls from './FilterControls';
import TopPostsSection from './TopPostsSection';
import LoadingAnimation from './LoadingAnimation';

const ProfilePostsSection = ({ stories, userFeed, loading }) => {
  const [filteredPosts, setFilteredPosts] = useState([]);
  const [filterCriteria, setFilterCriteria] = useState('likes');
  const [sortOrder, setSortOrder] = useState('desc');

  useEffect(() => {
    if (userFeed) {
      filterAndSortPosts(userFeed);
    }
  }, [userFeed, filterCriteria, sortOrder]);

  const filterAndSortPosts = (userFeed) => {
    let sorted = userFeed.user_posts.sort((a, b) => {
      if (filterCriteria === 'likes') {
        return sortOrder === 'desc' ? b.like_count - a.like_count : a.like_count - b.like_count;
      } else {
        return sortOrder === 'desc' ? b.comment_count - a.comment_count : a.comment_count - b.comment_count;
      }
    });
    setFilteredPosts(sorted);
  };

  return (
    <div>
      {loading.stories ? <LoadingAnimation /> : <StoriesSection stories={stories} />}
      <FilterControls
        filterCriteria={filterCriteria}
        setFilterCriteria={setFilterCriteria}
        sortOrder={sortOrder}
        setSortOrder={setSortOrder}
      />
      {loading.userFeed ? <LoadingAnimation /> : <TopPostsSection feed={filteredPosts} />}
    </div>
  );
};

export default ProfilePostsSection;
