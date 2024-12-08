import React, { useState } from 'react';
import { useDeposit } from '../../hooks/useDeposit';

const BalanceEdit = () => {
  const [balance, setBalance] = useState(100);
  const [newBalance, setNewBalance] = useState('');
  const [error, setError] = useState('');

  const {
    data: depositData,
    deposit,
    error: errorDeposit,
  } = useDeposit(balance);

  const handleInputChange = (e) => {
    const value = e.target.value;

    // Sadece sayılara ve en fazla 5 basamağa izin ver
    if (/^\d{0,5}$/.test(value)) {
      setNewBalance(value);
      setError('');
    } else {
      setError('Please enter a valid number (max 5 digits).');
    }
  };

  const handleConfirm = () => {
    if (newBalance === '') {
      setError('Input cannot be empty.');
      return;
    }

    const numericValue = parseInt(newBalance, 10);

    if (numericValue > 99999) {
      setError('Value exceeds the maximum limit (5 digits).');
    } else {
      deposit();
      setBalance(numericValue);
      setNewBalance('');
      setError('');
    }
  };

  return (
    <div>
      <h2>Balance Management</h2>
      <p>Current Balance: ${balance}</p>

      {/* Yeni bakiye girişi */}
      <div>
        <label>
          Enter New Balance:
          <input
            type='text'
            value={newBalance}
            onChange={handleInputChange}
            placeholder='Max 5 digits'
          />
        </label>
        <button onClick={handleConfirm}>Confirm</button>
        {error && <p style={{ color: 'red' }}>{error}</p>}
      </div>
    </div>
  );
};

export default BalanceEdit;
