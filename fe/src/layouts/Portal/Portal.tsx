import React from 'react';
import { NavLink, useNavigate } from 'react-router';
import { Outlet } from 'react-router';
import './portal.css';

const Portal = () => {
  const navigate = useNavigate();
  return (
    <>
      <div className='header-container'>
        <img
          onClick={() => navigate('/')}
          width={70}
          height={'auto'}
          src='https://d1yjjnpx0p53s8.cloudfront.net/styles/logo-original-577x577/s3/032014/untitled-1_70.png?itok=yv_awhaU'
          alt=''
        />

        <div className='right-side'>
          <button className='logout-button'>
            <NavLink to='password-change'>Change Password</NavLink>
          </button>
          <button className='myportal-button'>
            <NavLink to='balance-edit'>Deposit</NavLink>
          </button>
          <button className='logout-button'>
            {' '}
            <NavLink to='flight-tickets'>View Flights</NavLink>
          </button>
        </div>
      </div>
      <div className='outlet'>
        <Outlet />
      </div>
    </>
  );
};

export default Portal;
