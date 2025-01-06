import React from 'react';
import { useUser } from '../../context/UserContext';
import FlightList from '../FlightCard/FlightCard';
import NoFlight from '../NoFlight/NoFlight';
import Header from '../Header/Header';
import { useFilterFlight } from '../../hooks/useFilterFlight';
import { useSearchParams } from 'react-router';
import ImagesRow from '../ImagesRow/ImagesRow';

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
      <div className='flights'>
        {filterData?.filterFlightDataList?.length > 0 ? (
          filterData?.filterFlightDataList.map((flight) => (
            <FlightList flight={flight} key={flight.id} />
          ))
        ) : filterData?.filterFlightDataList?.length === 0 ? (
          <NoFlight />
        ) : (
          filterData?.flightDataList.map((flight) => (
            <FlightList flight={flight} key={flight.id} />
          ))
        )}
      </div>
    </>
  );
};

export default FilteredFlightList;
