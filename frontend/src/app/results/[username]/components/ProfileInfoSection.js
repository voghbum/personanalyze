import React from 'react';
import ProfileHeader from './ProfileHeader';

const ProfileInfoSection = ({ userInfo }) => {
  if (!userInfo) return null;

  return (
    <div className="mb-8">
      <ProfileHeader user={userInfo} />
    </div>
  );
};

export default ProfileInfoSection;
