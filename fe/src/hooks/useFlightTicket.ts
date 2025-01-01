import { useMutation } from '@tanstack/react-query';
import { fetchBuyFlightTicket } from '../services';

export const useFlightTicket = (
  flightId,
  userInfoFetch,
  userFlightListFetch
) => {
  const mutation = useMutation({
    mutationFn: async () => {
      const result = await fetchBuyFlightTicket(flightId);
      return result;
    },
    onSuccess: () => {
      userInfoFetch();
      userFlightListFetch();
    },
    retry: false,
  });

  return {
    data: mutation.data,
    getFlightTicket: mutation.mutate,
    error: mutation.error,
    isSuccess: mutation.isSuccess,
  };
};
