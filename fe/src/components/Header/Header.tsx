import React, { useEffect } from 'react';
import { Link, useNavigate } from 'react-router';
import './header.css';
import { useUserInfo } from '../../hooks/useUserInfo';
import { useLogout } from '../../hooks/useLogout';

const Header = () => {
  const navigate = useNavigate();
  const { data, isLoading } = useUserInfo();
  const { data: logoutData, logout, isSuccess } = useLogout();

  const logoutHandler = () => {
    logout();
  };

  const addFlight = () => {
    navigate('/add-flight');
  };

  useEffect(() => {
    if (logoutData && isSuccess) {
      localStorage.removeItem('token');
      navigate('/login');
    }
  }, [logoutData, isSuccess, navigate]);

  return (
    <div className='row'>
      <img
        width={50}
        height={'auto'}
        src='https://d1yjjnpx0p53s8.cloudfront.net/styles/logo-original-577x577/s3/032014/untitled-1_70.png?itok=yv_awhaU'
        alt=''
      />

      <div className='right-side'>
        <p onClick={addFlight}>Add Flight</p>
        <p>{data?.balance}$</p>
        <Link to='/portal'>My Portal</Link>
        <p onClick={logoutHandler}>logout</p>
      </div>
    </div>
  );
};

export default Header;
