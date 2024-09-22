import React from 'react';
import ProfileHeader from './ProfileHeader';
import PrivateAccountMessage from './PrivateAccountMessage';

const ProfileInfoSection = ({ userInfo }) => {
  if (!userInfo) return null;

  return (
    <div className="mb-8">
      <ProfileHeader user={userInfo} />
      {userInfo.is_private && <PrivateAccountMessage username={userInfo.username} />}
    </div>
  );
};

export default ProfileInfoSection;