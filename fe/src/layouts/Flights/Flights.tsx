import React, { useState } from 'react';
import Header from '../../components/Header/Header';
import { useFlightAll } from '../../hooks/useFlightAll';
import './flights.css';
import FlightList from '../../components/FlightList/FlightCard';
import FlightFilter from '../../components/FlightFilter/FlightFilter';
import { useFilterFlight } from '../../hooks/useFilterFlight';

const Flights = () => {
  const [filters, setFilters] = useState({
    departureCity: '',
    arrivalCity: '',
    date: '',
    maxPrice: '',
  });

  const {
    data: allFlightData,
    refetch,
    isLoading,
    error,
    isSuccess,
  } = useFlightAll();

  const { refetch: callFilterFlight, data: filterFlight } = useFilterFlight({
    departureCity: filters.departureCity,
    arrivalCity: filters.arrivalCity,
    departureDay: filters.date,
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
          <FlightFilter
            filters={filters}
            setFilters={setFilters}
            refetch={callFilterFlight}
          />
        </aside>
        {isSuccess && (filterFlight || allFlightData) && (
          <div className='flights'>
            {filterFlight?.filterFlightDataList?.length > 0
              ? filterFlight?.filterFlightDataList.map((flight) => (
                  <FlightList flight={flight} key={flight.id} />
                ))
              : isSuccess &&
                allFlightData?.flightDataList.map((flight) => (
                  <FlightList flight={flight} key={flight.id} />
                ))}
          </div>
        )}
      </div>
    </>
  );
};

export default Flights;
