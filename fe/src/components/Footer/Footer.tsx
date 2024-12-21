import React from 'react';
import './footer.css';

const Footer = () => {
  return (
    <div className='footer'>
      <img
        width={70}
        height={'auto'}
        src='https://d1yjjnpx0p53s8.cloudfront.net/styles/logo-original-577x577/s3/032014/untitled-1_70.png?itok=yv_awhaU'
        alt=''
      />
      <div className='footer-links'>
        <div className='footer-row'>
          <p>Genel Kurallar</p>
          <p>Gizlilik</p>
          <p>Uyumluluk</p>
        </div>
        <div>
          <p>Bize Yazın</p>
        </div>
      </div>
      <div className='footer-desc'>
        <p>TGB Hava Taşımacılığı A.Ş. Tüm Hakları Saklıdır © 2005 - 2023</p>
      </div>
    </div>
  );
};

export default Footer;
