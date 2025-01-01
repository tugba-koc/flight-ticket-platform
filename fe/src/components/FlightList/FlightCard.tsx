import React, { useEffect } from 'react';
import { useFlightTicket } from '../../hooks/useFlightTicket';
import './flightCard.css';
import { useUserInfo } from '../../hooks/useUserInfo';
import { useUser } from '../../context/UserContext';
import { useUserFlightList } from '../../hooks/useUserFlightList';

const FlightList = ({ flight, key }) => {
  const { dispatch } = useUser();

  const { refetch: userInfoFetch } = useUserInfo();
  const { refetch: userFlightListFetch } = useUserFlightList();
  const { getFlightTicket, isSuccess } = useFlightTicket(
    flight.id,
    userInfoFetch,
    userFlightListFetch
  );

  const purchaseFlightTicket = () => {
    getFlightTicket();
  };

  useEffect(() => {
    console.log('isSuccess', isSuccess);
    if (isSuccess) dispatch({ type: 'SET_MODAL', payload: isSuccess });
  }, [dispatch, isSuccess]);

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
