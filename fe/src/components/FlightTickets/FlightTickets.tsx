import React from 'react';
import { useUserFlightList } from '../../hooks/useUserFlightList';

const FlightTickets = () => {
  const { data: tickets, isSuccess } = useUserFlightList();

  console.log('tickets >>> ', tickets);

  return (
    <div>
      <h2>Your Flight Tickets</h2>
      <ul>
        {isSuccess &&
          tickets &&
          tickets?.flights.map((ticket) => (
            <li key={ticket.id}>
              Destination: {ticket.departureCity}, Date: {ticket.departureDay}
            </li>
          ))}
      </ul>
    </div>
  );
};

export default FlightTickets;
