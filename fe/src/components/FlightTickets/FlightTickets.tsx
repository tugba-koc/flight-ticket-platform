import React from 'react';
import { useUserFlightList } from '../../hooks/useUserFlightList';
import './flightTickets.css';
import { useRemoveFlightTicket } from '../../hooks/useRemoveFlightTicket';

const FlightTicketCard = ({ ticket }) => {
  console.log('ticket', ticket);
  const { removeFlightTicket } = useRemoveFlightTicket(ticket.id);

  const handleRemoveTicket = () => {
    removeFlightTicket();
  };

  return (
    <div className='flightCardTicket'>
      <p className='flightCardTicket-company'>{ticket?.company}</p>

      <p>{ticket?.departureCity}</p>
      <img
        className='flightCardTicket-image'
        width={'10%'}
        src='https://static.thenounproject.com/png/783874-200.png'
      />
      <p>{ticket?.arrivalCity}</p>
      <div className='flightCardTicket-details'>
        <p className='flightCardTicket-hour'>{ticket?.departureHour}</p>
        <p className='flightCardTicket-price'>{ticket?.price}$</p>
      </div>
      <div>
        <span onClick={handleRemoveTicket} class='close'>
          ×
        </span>
      </div>
    </div>
  );
};

const FlightTickets = () => {
  const { data: tickets, isSuccess } = useUserFlightList();

  return (
    <div>
      <h2>My Flight Tickets</h2>
      <ul className='flightTickets-list'>
        {isSuccess &&
          tickets &&
          tickets?.flights.map((ticket) => (
            <FlightTicketCard ticket={ticket} key={ticket.id} />
          ))}
      </ul>
    </div>
  );
};

export default FlightTickets;
