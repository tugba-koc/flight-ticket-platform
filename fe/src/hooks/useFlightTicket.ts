import { useMutation } from '@tanstack/react-query';
import { fetchFlightTicket } from '../services';

export const useFlightTicket = (flightId) => {
  const mutation = useMutation({
    mutationFn: async () => {
      const result = await fetchFlightTicket(flightId);
      return result;
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
