import React, { useState } from 'react';
import axios from 'axios';
import { useAddFlight } from '../../hooks/useAddFlight';

const cities = ['İstanbul', 'Ankara', 'İzmir', 'Erzurum', 'Trabzon'];

const AddFlight = () => {
  const [formData, setFormData] = useState({
    flightNumber: '',
    departureCity: '',
    arrivalCity: '',
    company: '',
    departureDay: '',
    departureHour: '',
    price: '',
  });

  const { addFlight } = useAddFlight(formData);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    addFlight();
  };

  return (
    <div style={{ maxWidth: '500px', margin: '0 auto' }}>
      <h2>Add Flight</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <input
            type='text'
            name='flightNumber'
            value={formData.flightNumber}
            onChange={handleChange}
            placeholder='Flight Number'
            required
          />
        </div>
        <div>
          <select
            name='departureCity'
            value={formData.departureCity}
            onChange={handleChange}
          >
            <option value=''>Çıkış Şehri (Tümü)</option>
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
            value={formData.arrivalCity}
            onChange={handleChange}
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
            type='text'
            name='company'
            value={formData.company}
            onChange={handleChange}
            placeholder='Company'
            required
          />
        </div>
        <div>
          <input
            type='date'
            name='departureDay'
            value={formData.departureDay}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <input
            type='time'
            name='departureHour'
            value={formData.departureHour}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <input
            type='number'
            name='price'
            value={formData.price}
            onChange={handleChange}
            placeholder='Price'
            required
          />
        </div>
        <button type='submit'>Save Flight</button>
      </form>
    </div>
  );
};

export default AddFlight;
