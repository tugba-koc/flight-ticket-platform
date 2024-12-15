import React from 'react';
import { useFlightTicket } from '../../hooks/useFlightTicket';

const FlightList = ({ flight, key }) => {
  const { getFlightTicket } = useFlightTicket(flight.id);

  const purchaseFlightTicket = (id) => {
    console.log('purchaseFlightTicket', id);
    getFlightTicket();
  };

  return (
    <div className='flightCard' key={key}>
      <p>from: {flight?.departureCity}</p>
      <p>to: {flight?.arrivalCity}</p>
      <p>company: {flight?.company}</p>
      <p>price: {flight?.price}</p>
      <button onClick={() => purchaseFlightTicket(flight.id)}>Purchase</button>
    </div>
  );
};

export default FlightList;
