import React from 'react';
import './flightFilter.css';
import { useNavigate } from 'react-router';

const cities = ['İstanbul', 'Ankara', 'İzmir', 'Erzurum', 'Trabzon'];

const FlightFilter = ({ setFilters, filters }) => {
  const navigate = useNavigate();

  const handleFilterChange = (e) => {
    const { name, value } = e.target;
    setFilters({ ...filters, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    navigate(
      `/flights?departureCity=${filters.departureCity}&arrivalCity=${filters.arrivalCity}&date=${filters.date}`
    );
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
