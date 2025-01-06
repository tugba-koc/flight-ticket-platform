import React, { useEffect, useState } from 'react';
import { useDeposit } from '../../hooks/useDeposit';
import './balanceEdit.css';
import { useUserInfo } from '../../hooks/useUserInfo';
import { useUser } from '../../context/UserContext';

const BalanceEdit = () => {
  const { data: userInfo, refetch: userInfoFetch } = useUserInfo();

  const { dispatch } = useUser();

  const [balance, setBalance] = useState(100);
  const [newBalance, setNewBalance] = useState('');
  const [error, setError] = useState('');

  const {
    data: depositData,
    deposit,
    error: errorDeposit,
  } = useDeposit(newBalance, userInfoFetch);

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
      dispatch({
        type: 'SET_MODAL',
        payload: {
          view: true,
          message: 'success',
        },
      });
    }
  }, [depositData]);

  const BTN_DISABLED = newBalance === '' || errorDeposit;

  return (
    <div>
      <h2>Balance Management</h2>
      <p>
        Current Balance:{' '}
        <span>{depositData?.newAmount ?? userInfo?.balance ?? '-'}$</span>
      </p>

      <form id='balance-edit' onSubmit={handleConfirm}>
        <div className='input-wrapper'>
          <input
            type='text'
            value={newBalance}
            onChange={handleInputChange}
            placeholder='Max 5 digits'
          />
        </div>
        {error && <p style={{ color: 'red' }}>{error}</p>}
        <button disabled={BTN_DISABLED} type='submit'>
          Deposit
        </button>
      </form>
    </div>
  );
};

export default BalanceEdit;
