import React from 'react';
import './modal.css';
import { useUser } from '../../context/UserContext';

const Modal = () => {
  const {
    state: {
      Modal: { isModalOpen, message },
    },
    dispatch,
  } = useUser();

  const handleCloseModal = () => {
    dispatch({
      type: 'SET_MODAL',
      payload: {
        view: false,
        message: null,
      },
    });
  };

  if (!isModalOpen) return null;
  return (
    <div id='myModal' className='modal'>
      <div className='modal-content'>
        <span onClick={handleCloseModal} className='close'>
          &times;
        </span>
        {message !== 'success' ? (
          <>
            <h4>Error!</h4>
            <p>Your operation can not be done. The problem is : {message}</p>
          </>
        ) : (
          <>
            <h4>Success!</h4>
            <p>Your operation has been successfully completed.</p>
          </>
        )}
      </div>
    </div>
  );
};

export default Modal;
