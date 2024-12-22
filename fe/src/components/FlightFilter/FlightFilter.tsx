import React from 'react';
import './flightFilter.css';
import { useQueryClient } from '@tanstack/react-query';

const cities = ['İstanbul', 'Ankara', 'İzmir', 'Erzurum', 'Trabzon'];

const FlightFilter = ({ setFilters, filters, refetch }) => {
  const queryClient = useQueryClient();

  const handleFilterChange = (e) => {
    const { name, value } = e.target;
    setFilters({ ...filters, [name]: value });
  };

  // It is a good practice to use queryKey to cache the data.
  const queryKey = [
    'filterFlight',
    filters.departureCity,
    filters.arrivalCity,
    filters.date,
  ];

  const handleSubmit = (e) => {
    e.preventDefault();

    const cachedData = queryClient.getQueryData(queryKey);

    if (cachedData) {
      console.log('there is cached data for this key', cachedData);
      return;
    }

    refetch();
  };

  return (
    <form id='formFilter' onSubmit={handleSubmit}>
      <div>
        <span>From</span>
        <select
          name='departureCity'
          value={filters.departureCity}
          onChange={handleFilterChange}
        >
          <option value=''>Departure City (All)</option>
          {cities.map((city) => (
            <option key={city} value={city}>
              {city}
            </option>
          ))}
        </select>
      </div>
      <div>
        <span>To</span>
        <select
          name='arrivalCity'
          value={filters.arrivalCity}
          onChange={handleFilterChange}
        >
          <option value=''>Arrival City (All)</option>
          {cities.map((city) => (
            <option key={city} value={city}>
              {city}
            </option>
          ))}
        </select>
      </div>
      <div>
        <span>Date</span>
        <input
          type='date'
          name='date'
          value={filters.date}
          onChange={handleFilterChange}
          placeholder='Tarih'
        />
      </div>
      <button type='submit'>Search Flight</button>
    </form>
  );
};

export default FlightFilter;
