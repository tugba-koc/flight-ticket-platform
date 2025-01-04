import React, { useEffect, useState } from 'react';
import { useResetPassword } from '../../hooks/useResetPassword';
import './passwordChange.css';
import { useUser } from '../../context/UserContext';

const PasswordChange = () => {
  const { dispatch } = useUser();

  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [error, setError] = useState('');

  const {
    data: resetPasswordData,
    resetPassword,
    isSuccess,
  } = useResetPassword({
    password,
    confirmPassword,
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    if (password === confirmPassword) {
      resetPassword();
    } else {
      setError('Passwords do not match.');
    }
  };

  useEffect(() => {
    if (resetPasswordData && isSuccess) {
      setConfirmPassword('');
      setPassword('');
      dispatch({
        type: 'SET_MODAL',
        payload: {
          view: true,
          message: 'success',
        },
      });
    }
  }, [resetPasswordData]);

  const BUTTON_DISABLED = !password || !confirmPassword;

  return (
    <div>
      <h2>Change Password</h2>
      <form id='changePassword' onSubmit={handleSubmit}>
        <div className='input-wrapper'>
          <input
            className={error ? 'error' : ''}
            type='password'
            value={password}
            onChange={(e) => {
              setPassword(e.target.value);
              setError('');
            }}
            placeholder='New Password'
          />
        </div>

        <div className='input-wrapper'>
          <input
            className={error ? 'error' : ''}
            type='password'
            value={confirmPassword}
            onChange={(e) => {
              setConfirmPassword(e.target.value);
              setError('');
            }}
            placeholder='Confirm New Password'
          />
        </div>
        {error && <p className='error-message'>{error}</p>}
        <button disabled={BUTTON_DISABLED} type='submit'>
          Change Password
        </button>
      </form>
    </div>
  );
};

export default PasswordChange;
