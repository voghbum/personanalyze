import React from 'react';

const PrivateAccountMessage = ({ username }) => {
  return (
    <div className="bg-gradient-to-r from-red-500 to-pink-500 text-white p-6 rounded-lg mb-8 shadow-lg">
      <h2 className="text-2xl font-bold mb-2">Gizli Hesap</h2>
      <p className="text-lg mb-4">
        @{username} kullanıcısının hesabı gizli.
      </p>
      <p className="text-base">
        Bu kullanıcının fotoğraflarını ve videolarını görmek için hesabı takip etmeniz gerekmektedir.
      </p>
    </div>
  );
};

export default PrivateAccountMessage;