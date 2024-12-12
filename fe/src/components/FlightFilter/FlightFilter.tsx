import React, { useState } from 'react';
import './flightFilter.css';
import { useFilterFlight } from '../../hooks/useFilterFlight';

const FlightFilter = () => {
  const [filters, setFilters] = useState({
    departureCity: '',
    arrivalCity: '',
    date: '',
    maxPrice: '',
  });

  const { refetch } = useFilterFlight({
    departureCity: filters.departureCity,
    arrivalCity: filters.arrivalCity,
    departureDay: filters.date,
  });

  const cities = ['İstanbul', 'Ankara', 'İzmir', 'Erzurum', 'Trabzon'];

  const handleFilterChange = (e) => {
    const { name, value } = e.target;
    setFilters({ ...filters, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    refetch();
  };

  return (
    <form id='formFilter' onSubmit={handleSubmit}>
      <div>
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
        <input
          type='date'
          name='date'
          value={filters.date}
          onChange={handleFilterChange}
          placeholder='Tarih'
        />
      </div>
      <div>
        <input
          type='number'
          name='maxPrice'
          value={filters.maxPrice}
          onChange={handleFilterChange}
          placeholder='Maksimum Fiyat'
        />
      </div>
      <button type='submit'>Filtrele</button>
    </form>
  );
};

export default FlightFilter;
