import React from 'react';
import FlightCard from '../FlightCard/FlightCard';
import NoFlight from '../NoFlight/NoFlight';
import Header from '../Header/Header';
import { useFilterFlight } from '../../hooks/useFilterFlight';
import { useSearchParams } from 'react-router';
import './filteredFlightList.css';

const FilteredFlightList = () => {
  const [searchParams] = useSearchParams();

  const departureCity = searchParams.get('departureCity');
  const arrivalCity = searchParams.get('arrivalCity');
  const date = searchParams.get('date');

  const { refetch: callFilterFlight, data: filterData } = useFilterFlight({
    departureCity: departureCity,
    arrivalCity: arrivalCity,
    departureDay: date,
  });

  React.useEffect(() => {
    if (departureCity && arrivalCity && date) {
      callFilterFlight();
    }
  }, [departureCity, arrivalCity, date, callFilterFlight]);

  return (
    <>
      <Header />
      <div className='hero-image'></div>
      <h3 className='filteredFlightList-header'>Choose your flights</h3>
      <div className='filteredFlightList-container'>
        <div className='filteredFlightList'>
          <div className='filteredFlightList-text'>
            <div
              style={{
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
              }}
            >
              <h4>{departureCity}</h4>
              <span>
                <img
                  className='switch-icon'
                  width={'30px'}
                  src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSi09vu7pWKVz3FpTlaljOcKknKDwEnruZsrA&s'
                  alt='switch icon'
                />
              </span>
              <h4>{arrivalCity}</h4>
            </div>
            <div>
              <p>When {date} </p>
            </div>
          </div>
        </div>

        <div className='flights'>
          {filterData?.filterFlightDataList?.length > 0 ? (
            filterData?.filterFlightDataList.map((flight) => (
              <FlightCard flight={flight} key={flight.id} />
            ))
          ) : filterData?.filterFlightDataList?.length === 0 ? (
            <NoFlight />
          ) : (
            filterData?.flightDataList.map((flight) => (
              <FlightCard flight={flight} key={flight.id} />
            ))
          )}
        </div>
      </div>
    </>
  );
};

export default FilteredFlightList;
