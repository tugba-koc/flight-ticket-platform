import React, { useState } from 'react';
import Header from '../../components/Header/Header';
import './flights.css';
import FlightFilter from '../../components/FlightFilter/FlightFilter';
import ImagesRow from '../../components/ImagesRow/ImagesRow';

const Flights = () => {
  const [filters, setFilters] = useState({
    departureCity: '',
    arrivalCity: '',
    date: '',
    maxPrice: '',
  });

  return (
    <>
      <Header />
      <img
        className='flights-hero'
        width={'100%'}
        height={'260px'}
        src='https://www.goindigo.in/content/dam/indigov2/6e-website/aboutus/flight-booking/International-flights.jpg'
        alt=''
      />
      <div id='flights-main'>
        <aside>
          <FlightFilter filters={filters} setFilters={setFilters} />
        </aside>
      </div>
      <ImagesRow />
    </>
  );
};

export default Flights;
