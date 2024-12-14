import React from 'react';

const FlightList = ({ flight, key }) => {
  return (
    <div className='flightCard' key={key}>
      <p>from: {flight?.departureCity}</p>
      <p>to: {flight?.arrivalCity}</p>
      <p>company: {flight?.company}</p>
      <p>price: {flight?.price}</p>
      <button>Purchase</button>
    </div>
  );
};

export default FlightList;
