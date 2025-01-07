import React, { useEffect, useState } from 'react';
import { useAddFlight } from '../../hooks/useAddFlight';
import './addFlight.css';
import Header from '../../components/Header/Header';

const cities = ['İstanbul', 'Ankara', 'İzmir', 'Erzurum', 'Trabzon'];
const companies = ['THY', 'Pegasus', 'AnadoluJet', 'SunExpress'];

const AddFlight = () => {
  const [error, setError] = useState(null);
  const [formData, setFormData] = useState({
    flightNumber: '',
    departureCity: '',
    arrivalCity: '',
    company: '',
    departureDay: '',
    departureHour: '',
    price: '',
  });

  const { data, addFlight, error: addFlightError } = useAddFlight(formData);

  const BUTTON_DISABLED = !formData.flightNumber || !formData.price;

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

  useEffect(() => {
    setError(addFlightError);
  }, [addFlightError]);

  useEffect(() => {
    if (data) {
      setFormData({
        flightNumber: '',
        departureCity: '',
        arrivalCity: '',
        company: '',
        departureDay: '',
        departureHour: '',
        price: '',
      });
    }
  }, [data]);

  return (
    <>
      <Header />
      <div style={{ maxWidth: '500px', margin: '0 auto' }}>
        <h2>Add Flight</h2>
        <form id='addFlight' onSubmit={handleSubmit}>
          <div className='input-wrapper'>
            <input
              type='text'
              name='flightNumber'
              value={formData.flightNumber}
              onChange={handleChange}
              placeholder='Flight Number'
              required
            />
          </div>
          <div className='input-wrapper'>
            <select
              name='departureCity'
              value={formData.departureCity}
              onChange={handleChange}
            >
              <option value=''>Departure City (All)</option>
              {cities.map((city) => (
                <option key={city} value={city}>
                  {city}
                </option>
              ))}
            </select>
          </div>
          <div className='input-wrapper'>
            <select
              name='arrivalCity'
              value={formData.arrivalCity}
              onChange={handleChange}
            >
              <option value=''>Arrival City (All)</option>
              {cities.map((city) => (
                <option key={city} value={city}>
                  {city}
                </option>
              ))}
            </select>
          </div>
          <div className='input-wrapper'>
            <select
              name='company'
              value={formData.company}
              onChange={handleChange}
            >
              <option value=''>Company (All)</option>
              {companies.map((company) => (
                <option key={company} value={company}>
                  {company}
                </option>
              ))}
            </select>
          </div>
          <div className='input-wrapper'>
            <input
              type='date'
              name='departureDay'
              value={formData.departureDay}
              onChange={handleChange}
              required
            />
          </div>
          <div className='input-wrapper'>
            <input
              type='time'
              name='departureHour'
              value={formData.departureHour}
              onChange={handleChange}
              required
            />
          </div>
          <div className='input-wrapper'>
            <input
              type='number'
              name='price'
              value={formData.price}
              onChange={handleChange}
              placeholder='Price'
              required
            />
          </div>
          {error && <p className='error-message'>{error?.error}</p>}
          <button disabled={BUTTON_DISABLED} type='submit'>
            Save Flight
          </button>
        </form>
      </div>
    </>
  );
};

export default AddFlight;
