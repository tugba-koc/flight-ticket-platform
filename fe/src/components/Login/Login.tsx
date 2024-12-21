import React, { useEffect, useState } from 'react';
import './login.css';
import { Link, useNavigate } from 'react-router';
import { useLogin } from '../../hooks/useLogin';
import { useUser } from '../../context/UserContext';

const Login = () => {
  const navigate = useNavigate();
  const { dispatch } = useUser();

  const [formData, setFormData] = useState({
    email: '',
    password: '',
  });

  const BUTTON_DISABLED = !formData.email || !formData.password;

  const { data: loginData, login, error: errorLogin } = useLogin(formData);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    login();
  };

  useEffect(() => {
    if (loginData?.token !== null && loginData?.token !== undefined) {
      localStorage.setItem('token', loginData.token);
      dispatch({ type: 'SET_ROLE', payload: loginData.role });
      navigate('/flights');
    }
  }, [navigate, loginData, dispatch]);

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
          <h2>Login</h2>
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
              type='password'
              id='password'
              name='password'
              value={formData.password}
              onChange={handleChange}
              placeholder='Password'
              required
            />
          </div>
          {errorLogin && <p>Error occured</p>}
          <button disabled={BUTTON_DISABLED} type='submit'>
            Submit
          </button>

          <div className='registration-link'>
            <p>You have already signed Up?</p>
            <span>
              <Link to='/register'>Register</Link>
            </span>
          </div>
        </form>
      </div>
    </>
  );
};

export default Login;
