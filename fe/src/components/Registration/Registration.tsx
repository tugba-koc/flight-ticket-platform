import React, { useEffect, useState } from 'react';
import './registration.css';
import { useRegister } from '../../hooks/useRegister';
import { Link, useNavigate } from 'react-router';

const Registration = () => {
  const navigate = useNavigate();

  const [error, setError] = useState(null);
  const [formData, setFormData] = useState({
    turkishId: '',
    name: '',
    surname: '',
    email: '',
    password: '',
    phoneNumber: '',
    birthDate: '',
    gender: '',
  });

  const BUTTON_DISABLED =
    !formData.turkishId ||
    !formData.name ||
    !formData.surname ||
    !formData.email ||
    !formData.password ||
    !formData.phoneNumber ||
    !formData.birthDate;

  const {
    data: registerData,
    register,
    error: registerError,
  } = useRegister(formData);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
    setError(null);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    register();
  };

  useEffect(() => {
    if (registerData) {
      navigate('/login');
    }
  }, [navigate, registerData]);

  useEffect(() => {
    setError(registerError);
  }, [registerError]);

  return (
    <>
      <div className='registration-main-wrapper'>
        <img
          className='registration-image'
          height={'auto'}
          src='https://travel-made-simple.com/wp-content/uploads/2019/03/layover-perks-free-hotels.jpg'
          alt=''
        />

        <form id='registration' onSubmit={handleSubmit}>
          <h2>Sign Up</h2>

          <div className='input-wrapper'>
            <input
              type='text'
              id='name'
              name='name'
              value={formData.name}
              onChange={handleChange}
              placeholder='Name'
              required
            />
          </div>

          <div className='input-wrapper'>
            <input
              type='text'
              id='surname'
              name='surname'
              value={formData.surname}
              onChange={handleChange}
              placeholder='Surname'
              required
            />
          </div>

          <div className='input-wrapper'>
            <input
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
              type='text'
              id='turkishId'
              name='turkishId'
              value={formData.turkishId}
              onChange={handleChange}
              placeholder='Turkish ID'
              maxLength={11}
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

          <div className='input-wrapper'>
            <input
              type='password'
              id='password'
              name='password'
              value={formData.password}
              onChange={handleChange}
              placeholder='Password'
              required
            />
          </div>

          <div className='input-wrapper'>
            <select
              id='gender'
              name='gender'
              value={formData.gender}
              onChange={handleChange}
              required
            >
              <option value=''>Select Gender</option>
              <option value='male'>Male</option>
              <option value='female'>Female</option>
              <option value='other'>Other</option>
            </select>
          </div>
          {error && <p className='error-message'>{error?.errors}</p>}
          <button disabled={BUTTON_DISABLED} type='submit'>
            Submit
          </button>
          <div className='login-link'>
            <p>You have already signed Up?</p>
            <span>
              <Link to='/login'>Login</Link>
            </span>
          </div>
        </form>
      </div>
    </>
  );
};

export default Registration;
