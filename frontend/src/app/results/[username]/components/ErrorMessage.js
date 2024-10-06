import React from 'react';
import './ErrorMessage.css'; // Import CSS for styling

const ErrorMessage = ({ message }) => {
  return (
    <div className="error-message">
      {message}
    </div>
  );
};

export default ErrorMessage;