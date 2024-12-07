import React from 'react';
import { Link } from 'react-router';
import './header.css';

const Header = () => {
  return (
    <div className='row'>
      <img
        width={50}
        height={'auto'}
        src='https://d1yjjnpx0p53s8.cloudfront.net/styles/logo-original-577x577/s3/032014/untitled-1_70.png?itok=yv_awhaU'
        alt=''
      />

      <Link to='/portal'>My Portal</Link>
    </div>
  );
};

export default Header;
