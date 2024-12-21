import React, { useEffect } from 'react';
import { Link, useNavigate } from 'react-router';
import './header.css';
import { useUserInfo } from '../../hooks/useUserInfo';
import { useLogout } from '../../hooks/useLogout';
import { useUser } from '../../context/UserContext';

const Header = () => {
  const navigate = useNavigate();
  const { state } = useUser();
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

  const toUppercase = (str) => {
    return str?.charAt(0).toUpperCase() + str?.slice(1);
  };

  return (
    <div className='header-container'>
      <img
        width={70}
        height={'auto'}
        src='https://d1yjjnpx0p53s8.cloudfront.net/styles/logo-original-577x577/s3/032014/untitled-1_70.png?itok=yv_awhaU'
        alt=''
      />

      <div className='right-side'>
        {state.role === 'ADMIN' && <p onClick={addFlight}>Add Flight</p>}

        <div className='user-info'>
          <p className='user-name'>
            {toUppercase(data?.name)} {toUppercase(data?.surname)}
          </p>
          <p>{data?.balance}$</p>
        </div>

        <button className='myportal-button'>
          <Link to='/portal'>My Portal</Link>
        </button>
        <button className='logout-button' onClick={logoutHandler}>
          logout
        </button>
      </div>
    </div>
  );
};

export default Header;
