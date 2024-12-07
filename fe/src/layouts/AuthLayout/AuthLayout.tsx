import React, { useEffect } from 'react';
import { Outlet, useNavigate } from 'react-router';
import './authLayout.css';

const AuthLayout = () => {
  const navigate = useNavigate();

  useEffect(() => {
    if (localStorage.getItem('token')) {
      navigate('/flights', { replace: true });
    }
  }, [navigate]);

  return (
    <div>
      <header>
        <img
          width={70}
          height={'auto'}
          src='https://d1yjjnpx0p53s8.cloudfront.net/styles/logo-original-577x577/s3/032014/untitled-1_70.png?itok=yv_awhaU'
          alt=''
        />
      </header>
      <main>
        <Outlet />
      </main>
    </div>
  );
};

export default AuthLayout;
