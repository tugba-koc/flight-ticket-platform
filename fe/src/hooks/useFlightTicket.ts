import { useMutation } from '@tanstack/react-query';
import { fetchBuyFlightTicket } from '../services';

export const useFlightTicket = (flightId, userInfoFetch) => {
  const mutation = useMutation({
    mutationFn: async () => {
      const result = await fetchBuyFlightTicket(flightId);
      return result;
    },
    onSuccess: () => {
      userInfoFetch();
    },
    retry: false,
  });

  return {
    data: mutation.data,
    getFlightTicket: mutation.mutate,
    isLoading: mutation.isLoading,
    error: mutation.error,
  };
};
