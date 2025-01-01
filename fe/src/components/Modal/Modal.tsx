import React from 'react';
import './modal.css';
import { useUser } from '../../context/UserContext';

const Modal = () => {
  const {
    state: { isModalOpen },
    dispatch,
  } = useUser();

  const handleCloseModal = () => {
    dispatch({ type: 'SET_MODAL', payload: false });
  };

  if (!isModalOpen) return null;
  return (
    <div id='myModal' class='modal'>
      <div class='modal-content'>
        <span onClick={handleCloseModal} class='close'>
          &times;
        </span>
        <h4>SUCCESS!</h4>
        <p>It's done!</p>
      </div>
    </div>
  );
};

export default Modal;
