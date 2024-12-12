import React from 'react';
import Header from '../../components/Header/Header';
import { useFlightAll } from '../../hooks/useFlightAll';
import './flights.css';
import FlightList from '../../components/FlightList/FlightList';
import FlightFilter from '../../components/FlightFilter/FlightFilter';

const Flights = () => {
  const {
    data: allFlightData,
    refetch,
    isLoading,
    error,
    isSuccess,
  } = useFlightAll();

  console.log('allFlightData >>> ', allFlightData);

  return (
    <>
      <Header />
      <h2>Flights</h2>
      <div id='flights-main'>
        <aside>
          <FlightFilter />
        </aside>
        {isSuccess && allFlightData && (
          <div className='flights'>
            {allFlightData?.flightDataList.map((flight) => (
              <FlightList flight={flight} key={flight.id} />
            ))}
          </div>
        )}
      </div>
    </>
  );
};

export default Flights;
