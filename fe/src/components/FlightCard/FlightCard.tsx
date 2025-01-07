import React, { useEffect } from 'react';
import { useFlightTicket } from '../../hooks/useFlightTicket';
import './flightCard.css';
import { useUserInfo } from '../../hooks/useUserInfo';
import { useUser } from '../../context/UserContext';
import { useUserFlightList } from '../../hooks/useUserFlightList';

const FlightCard = ({ flight, key }) => {
  const { dispatch } = useUser();

  const { refetch: userInfoFetch } = useUserInfo();
  const { refetch: userFlightListFetch } = useUserFlightList();
  const {
    getFlightTicket,
    isSuccess,
    error: flightTicketError,
  } = useFlightTicket(flight.id, userInfoFetch, userFlightListFetch);

  const purchaseFlightTicket = () => {
    getFlightTicket();
  };

  useEffect(() => {
    if (isSuccess) {
      console.log('düştü');
      dispatch({
        type: 'SET_MODAL',
        payload: {
          view: true,
          message: 'success',
        },
      });
    }
  }, [dispatch, isSuccess]);

  useEffect(() => {
    if (flightTicketError?.error && flightTicketError?.status === 422)
      dispatch({
        type: 'SET_MODAL',
        payload: {
          view: true,
          message: flightTicketError?.error,
        },
      });
  }, [dispatch, flightTicketError?.error]);

  return (
    <div className='flightCard' key={key}>
      <p>{flight?.departureCity}</p>
      <img
        className='flightCard-image'
        width={'15%'}
        src='https://static.thenounproject.com/png/783874-200.png'
      />
      <p>{flight?.arrivalCity}</p>
      <p className='flightCard-company'>{flight?.company}</p>

      <div className='flightCard-details'>
        <p className='flightCard-price'>{flight?.price}$</p>
        <button
          className='purchase-button'
          onClick={() => {
            localStorage.getItem('token')
              ? purchaseFlightTicket(flight.id)
              : dispatch({
                  type: 'SET_MODAL',
                  payload: {
                    view: true,
                    message: 'Please login to purchase',
                  },
                });
          }}
        >
          Purchase
        </button>
      </div>
    </div>
  );
};

export default FlightCard;
