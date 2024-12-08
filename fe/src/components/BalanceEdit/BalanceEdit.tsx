import React, { useEffect, useState } from 'react';
import { useDeposit } from '../../hooks/useDeposit';
import './balanceEdit.css';

const BalanceEdit = () => {
  const [balance, setBalance] = useState(100);
  const [newBalance, setNewBalance] = useState('');
  const [error, setError] = useState('');

  const {
    data: depositData,
    deposit,
    error: errorDeposit,
  } = useDeposit(newBalance);

  const handleInputChange = (e) => {
    const value = e.target.value;

    if (/^\d{0,5}$/.test(value)) {
      setNewBalance(value);
      setError('');
    } else {
      setError('Please enter a valid number (max 5 digits).');
    }
  };

  const handleConfirm = (e) => {
    e.preventDefault();
    if (newBalance === '') {
      setError('Input cannot be empty.');
      return;
    }

    const numericValue = parseInt(newBalance, 10);

    if (numericValue > 99999) {
      setError('Value exceeds the maximum limit (5 digits).');
    } else {
      deposit();
    }
  };

  useEffect(() => {
    if (depositData) {
      setBalance(depositData?.newAmount);
      setNewBalance('');
      setError('');
    }
  }, [depositData]);

  return (
    <div>
      <h2>Balance Management</h2>
      <p>
        Current Balance: <span>${balance}</span>
      </p>

      <form onSubmit={handleConfirm}>
        <input
          type='text'
          value={newBalance}
          onChange={handleInputChange}
          placeholder='Max 5 digits'
        />
        <br />
        {error && <p style={{ color: 'red' }}>{error}</p>}
        <button type='submit'>Deposit</button>
      </form>
    </div>
  );
};

export default BalanceEdit;
