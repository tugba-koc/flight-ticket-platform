import React, { useEffect, useState } from 'react';
import './forgotPassword.css';
import { useNavigate } from 'react-router';
import { useUser } from '../../context/UserContext';
import { useForgotPasswordCheck } from '../../hooks/useForgotPasswordCheck';
import { useForgotPasswordUpdate } from '../../hooks/useForgotPasswordUpdate';
import UpdateComponent from './UpdateComponent/UpdateComponent';

const ForgotPassword = () => {
  const navigate = useNavigate();
  const { dispatch } = useUser();

  const { forgotPasswordUpdate } = useForgotPasswordUpdate();

  const [formData, setFormData] = useState({
    email: '',
    phoneNumber: '',
    birthDate: '',
  });

  const [error, setError] = useState(null);

  const BUTTON_DISABLED =
    error || !formData.email || !formData.phoneNumber || !formData.birthDate;

  const {
    forgotPasswordCheck,
    error: errorForgotPasswordCheck,
    isSuccess: isSuccessForgotPasswordCheck,
  } = useForgotPasswordCheck(formData);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
    setError(null);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    await forgotPasswordCheck();
  };

  useEffect(() => {
    if (errorForgotPasswordCheck) {
      setError(errorForgotPasswordCheck);
    }
  }, [errorForgotPasswordCheck]);

  if (isSuccessForgotPasswordCheck) { // show forgot password update
    return <UpdateComponent />;
  }

  return (
    <>
      <div className='login-main-wrapper'>
        <img
          className='login-image'
          height={'auto'}
          src='https://travel-made-simple.com/wp-content/uploads/2019/03/layover-perks-free-hotels.jpg'
          alt=''
        />

        <form id='login' onSubmit={handleSubmit}>
          <h2>Forget Password</h2>
          <div className='input-wrapper'>
            <input
              className={error ? 'error' : ''}
              type='email'
              id='email'
              name='email'
              value={formData.email}
              onChange={handleChange}
              placeholder='Email'
              required
            />
          </div>
          <div className='input-wrapper'>
            <input
              type='tel'
              id='phoneNumber'
              name='phoneNumber'
              value={formData.phoneNumber}
              onChange={handleChange}
              placeholder='Phone Number'
              required
            />
          </div>

          <div className='input-wrapper'>
            <input
              type='date'
              id='birthDate'
              name='birthDate'
              value={formData.birthDate}
              placeholder='Birth Date'
              onChange={handleChange}
              required
            />
          </div>
          {error && <p className='error-message'>{error?.error}</p>}
          <button disabled={BUTTON_DISABLED} type='submit'>
            Submit
          </button>
        </form>
      </div>
    </>
  );
};

export default ForgotPassword;
