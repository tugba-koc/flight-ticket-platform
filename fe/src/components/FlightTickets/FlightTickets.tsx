import React from 'react';

const FlightTickets = () => {
  const tickets = [
    { id: 1, destination: 'New York', date: '2024-12-15' },
    { id: 2, destination: 'London', date: '2024-12-20' },
    { id: 3, destination: 'Tokyo', date: '2025-01-10' },
  ];

  return (
    <div>
      <h2>Your Flight Tickets</h2>
      <ul>
        {tickets.map((ticket) => (
          <li key={ticket.id}>
            Destination: {ticket.destination}, Date: {ticket.date}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default FlightTickets;
