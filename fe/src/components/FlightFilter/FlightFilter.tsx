import React from 'react';
import './flightFilter.css';

const cities = ['İstanbul', 'Ankara', 'İzmir', 'Erzurum', 'Trabzon'];

const FlightFilter = ({ setFilters, filters, refetch }) => {
  const handleFilterChange = (e) => {
    const { name, value } = e.target;
    setFilters({ ...filters, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('filter tetiklendi');
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
          <option value=''>Kalkış Şehri (Tümü)</option>
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
          <option value=''>Varış Şehri (Tümü)</option>
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
      <button type='submit'>Filtrele</button>
    </form>
  );
};

export default FlightFilter;
