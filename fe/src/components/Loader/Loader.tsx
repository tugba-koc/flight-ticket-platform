import { useIsFetching, useIsMutating } from '@tanstack/react-query';
import React from 'react';
import './loader.css';

const Loader = () => {
  const isFetching = useIsFetching();
  const isMutating = useIsMutating();

  return isFetching || isMutating ? (
    <div className='loader-overlay'>
      <span className='loader'></span>{' '}
    </div>
  ) : null;
};

export default Loader;
