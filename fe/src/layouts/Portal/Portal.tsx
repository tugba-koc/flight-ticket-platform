import React from 'react';
import { Link, NavLink } from 'react-router';
import { Outlet } from 'react-router';
import './portal.css';

const Portal = () => {
  return (
    <>
      <div className='row'>
        <h1>
          <Link to='/flights'>Hesabım</Link>
        </h1>
        <nav>
          <ul>
            <li>
              <NavLink to='password-change'>Change Password</NavLink>
            </li>
            <li>
              <NavLink to='balance-edit'>Edit Balance</NavLink>
            </li>
            <li>
              <NavLink to='flight-tickets'>View Flight Tickets</NavLink>
            </li>
          </ul>
        </nav>
      </div>
      <div className='outlet'>
        <Outlet />
      </div>
    </>
  );
};

export default Portal;
