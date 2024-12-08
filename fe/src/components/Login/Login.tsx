import React, { useEffect, useState } from 'react';
import './login.css';
import { Link, useNavigate } from 'react-router';
import { useLogin } from '../../hooks/useLogin';

const Login = () => {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    email: '',
    password: '',
  });

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
  console.log('loginData', loginData);
  console.log('errorLogin', errorLogin);

  useEffect(() => {
    if (loginData?.token !== null && loginData?.token !== undefined) {
      localStorage.setItem('token', loginData.token);
      navigate('/flights');
    }
  }, [navigate, loginData]);

  return (
    <>
      <form onSubmit={handleSubmit}>
        <div>
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

        <div>
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
        <button type='submit'>Submit</button>
      </form>
      <p>You haven't signed up?</p>
      <span>
        go to <Link to='/'> Register</Link>
      </span>
    </>
  );
};

export default Login;
