import React from 'react';

const PrivateAccountMessage = ({ username }) => {
  return (
    <div className="bg-red-500 text-white p-4 rounded-lg mb-6">
      <p className="text-lg font-semibold">
        @{username}'s account is private.
      </p>
      <p className="mt-2">
        Follow this account to see their photos and videos.
      </p>
    </div>
  );
};

export default PrivateAccountMessage;