import React, { useState } from 'react';
import Header from '../../components/Header/Header';
import { useFlightAll } from '../../hooks/useFlightAll';
import './flights.css';
import FlightList from '../../components/FlightList/FlightList';
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
      <h2>Flights</h2>
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
              : isSuccess && allFlightData?.flightDataList.map((flight) => (
                  <FlightList flight={flight} key={flight.id} />
                ))}
          </div>
        )}
      </div>
    </>
  );
};

export default Flights;
