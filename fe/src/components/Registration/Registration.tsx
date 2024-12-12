import React, { useEffect, useState } from 'react';
import './registration.css';
import { useRegister } from '../../hooks/useRegister';
import { Link, useNavigate } from 'react-router';

const Registration = () => {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    turkishId: '',
    name: '',
    surname: '',
    email: '',
    password: '',
    phoneNumber: '',
    address: '',
    birthDate: '',
    role: '',
    gender: '',
  });

  const { data: registerData, register } = useRegister(formData);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    register();
  };

  useEffect(() => {
    if (registerData) {
      navigate('/login');
    }
  }, [navigate, registerData]);

  return (
    <>
      <form id='registration' onSubmit={handleSubmit}>
        <div>
          <input
            type='text'
            id='turkishId'
            name='turkishId'
            value={formData.turkishId}
            onChange={handleChange}
            placeholder='Turkish ID'
            maxLength={11}
            required
          />
        </div>

        <div>
          <input
            type='text'
            id='name'
            name='name'
            value={formData.name}
            onChange={handleChange}
            placeholder='Name'
            required
          />
        </div>

        <div>
          <input
            type='text'
            id='surname'
            name='surname'
            value={formData.surname}
            onChange={handleChange}
            placeholder='Surname'
            required
          />
        </div>

        <div>
          <input
            type='email'
            id='email'
            name='email'
            value={formData.email}
            onChange={handleChange}
            placeholder='Email'
            required
          />
        </div>

        <div>
          <input
            type='password'
            id='password'
            name='password'
            value={formData.password}
            onChange={handleChange}
            placeholder='Password'
            required
          />
        </div>

        <div>
          <input
            type='tel'
            id='phoneNumber'
            name='phoneNumber'
            value={formData.phoneNumber}
            onChange={handleChange}
            placeholder='Phone Number'
            required
          />
        </div>

        <div>
          <input
            type='text'
            id='address'
            name='address'
            value={formData.address}
            onChange={handleChange}
            placeholder='Address'
            required
          />
        </div>

        <div>
          <input
            type='date'
            id='birthDate'
            name='birthDate'
            value={formData.birthDate}
            placeholder='Birth Date'
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <select
            id='role'
            name='role'
            value={formData.role}
            onChange={handleChange}
            required
          >
            <option value=''>Select Role</option>
            <option value='user'>User</option>
            <option value='admin'>Admin</option>
          </select>
        </div>

        <div>
          <select
            id='gender'
            name='gender'
            value={formData.gender}
            onChange={handleChange}
            required
          >
            <option value=''>Select Gender</option>
            <option value='male'>Male</option>
            <option value='female'>Female</option>
            <option value='other'>Other</option>
          </select>
        </div>

        <button type='submit'>Submit</button>
      </form>
      <p>You have already signed Up?</p>
      <span>
        go to <Link to='/login'> Login</Link>
      </span>
    </>
  );
};

export default Registration;
