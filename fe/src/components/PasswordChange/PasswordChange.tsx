import React, { useEffect, useState } from 'react';
import { useResetPassword } from '../../hooks/useResetPassword';
import './passwordChange.css';

const PasswordChange = () => {
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [message, setMessage] = useState('');

  const { data: resetPasswordData, resetPassword } = useResetPassword({
    password,
    confirmPassword,
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    if (password === confirmPassword) {
      setMessage('Password changed successfully!');
      resetPassword();
    } else {
      setMessage('Passwords do not match.');
    }
  };

  useEffect(() => {
    setConfirmPassword('');
    setPassword('');
  }, [resetPasswordData]);

  return (
    <div>
      <h2>Change Password</h2>
      <form id='changePassword' onSubmit={handleSubmit}>
        <div className='input-wrapper'>
          <input
            type='password'
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder='New Password'
          />
        </div>

        <div className='input-wrapper'>
          <input
            type='password'
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
            placeholder='Confirm New Password'
          />
        </div>
        <button type='submit'>Change Password</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
};

export default PasswordChange;
