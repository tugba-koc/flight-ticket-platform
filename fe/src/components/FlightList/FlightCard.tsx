import React from 'react';
import { useFlightTicket } from '../../hooks/useFlightTicket';
import './flightCard.css';

const FlightList = ({ flight, key }) => {
  const { getFlightTicket } = useFlightTicket(flight.id);

  const purchaseFlightTicket = () => {
    getFlightTicket();
  };

  return (
    <div className='flightCard' key={key}>
      <p className='flightCard-company'>{flight?.company}</p>

      <p>{flight?.departureCity}</p>
      <img
        className='flightCard-image'
        width={'15%'}
        src='https://static.thenounproject.com/png/783874-200.png'
      />
      <p>{flight?.arrivalCity}</p>
      <div className='flightCard-details'>
        <p className='flightCard-price'>{flight?.price}$</p>
        <button
          className='purchase-button'
          onClick={() => purchaseFlightTicket(flight.id)}
        >
          Purchase
        </button>
      </div>
    </div>
  );
};

export default FlightList;
