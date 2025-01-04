import React, { useEffect, useState } from 'react';
import './registration.css';
import { useRegister } from '../../hooks/useRegister';
import { Link, useNavigate } from 'react-router';
import { useValidateEmail } from '../../hooks/useValidateEmail';
import { useUser } from '../../context/UserContext';
import { useValidateTurkishId } from '../../hooks/useValidateTurkishId';
import { useValidatePhoneNumber } from '../../hooks/useValidatePhoneNumber';

const Registration = () => {
  const navigate = useNavigate();

  const { state, dispatch } = useUser();

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
    !formData.birthDate ||
    state?.formError?.email ||
    state?.formError?.turkishId ||
    state?.formError?.phoneNumber;

  const {
    data: registerData,
    register,
    error: registerError,
  } = useRegister(formData);
  const { refetch: validateEmail, error: validateEmailError } =
    useValidateEmail(formData?.email);
  const { refetch: validateTurkishId, error: validateTurkishIdError } =
    useValidateTurkishId(formData?.turkishId);
  const { refetch: validatePhoneNumber, error: validatePhoneNumberError } =
    useValidatePhoneNumber(formData?.phoneNumber);

  useEffect(() => {
    if (validateEmailError) {
      dispatch({
        type: 'SET_FORM_ERROR',
        payload: { email: validateEmailError?.error },
      });
    }
    if (validateTurkishIdError) {
      dispatch({
        type: 'SET_FORM_ERROR',
        payload: { turkishId: validateTurkishIdError?.error },
      });
    }
    if (validatePhoneNumberError) {
      dispatch({
        type: 'SET_FORM_ERROR',
        payload: { phoneNumber: validatePhoneNumberError?.error },
      });
    }
  }, [
    dispatch,
    validateEmailError,
    validatePhoneNumberError,
    validateTurkishIdError,
  ]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
    setError(null);
    dispatch({
      type: 'SET_FORM_ERROR',
      payload: { [name]: '' },
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    register();
  };

  useEffect(() => {
    if (registerData) {
      navigate('/auth/login');
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
              onBlur={() => {
                if (
                  formData.email &&
                  formData.email !== state?.formData?.email
                ) {
                  validateEmail();
                  dispatch({
                    type: 'SET_FORM_DATA',
                    payload: { email: formData.email },
                  });
                }
              }}
              className={state?.formError?.email ? 'error-input' : ''}
              type='email'
              id='email'
              name='email'
              value={formData.email}
              onChange={handleChange}
              placeholder='Email'
              required
            />
            <p className='error-text'>{state?.formError?.email}</p>
          </div>

          <div className='input-wrapper'>
            <input
              onBlur={() => {
                if (
                  formData.turkishId &&
                  formData.turkishId !== state?.formData?.turkishId &&
                  formData.turkishId.length === 11
                ) {
                  validateTurkishId();
                  dispatch({
                    type: 'SET_FORM_DATA',
                    payload: { turkishId: formData?.turkishId },
                  });
                }
              }}
              type='text'
              id='turkishId'
              name='turkishId'
              value={formData.turkishId}
              onChange={handleChange}
              placeholder='Turkish ID'
              maxLength={11}
              required
            />
            <p className='error-text'>{state?.formError?.turkishId}</p>
          </div>

          <div className='input-wrapper'>
            <input
              onBlur={() => {
                if (
                  formData.phoneNumber &&
                  formData.phoneNumber !== state?.formData?.phoneNumber
                ) {
                  validatePhoneNumber();
                  dispatch({
                    type: 'SET_FORM_DATA',
                    payload: { phoneNumber: formData.phoneNumber },
                  });
                }
              }}
              type='tel'
              id='phoneNumber'
              name='phoneNumber'
              value={formData.phoneNumber}
              onChange={handleChange}
              placeholder='Phone Number'
              required
            />
            <p className='error-text'>{state?.formError?.phoneNumber}</p>
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
          {error && <p className='error-message'>{error?.error}</p>}
          <button disabled={BUTTON_DISABLED} type='submit'>
            Submit
          </button>
          <div className='login-link'>
            <p>You have already signed up?</p>
            <span>
              <Link to='/auth/login'>Login</Link>
            </span>
          </div>
        </form>
      </div>
    </>
  );
};

export default Registration;
