import React from 'react';
import Header from '../../components/Header/Header';
import { Outlet } from 'react-router';

const Flights = () => {
  return (
    <>
      <Header />
      <main>
        <Outlet />
      </main>
    </>
  );
};

export default Flights;
