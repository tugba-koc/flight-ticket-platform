import { useMutation, useQueryClient } from '@tanstack/react-query';
import { fetchRemoveFlightTicket } from '../services';

export const useRemoveFlightTicket = (ticketId) => {
  const queryClient = useQueryClient();

  const mutation = useMutation({
    mutationFn: async () => {
      const result = await fetchRemoveFlightTicket(ticketId);
      return result;
    },
    retry: false,
    onSuccess: () => {
      queryClient.invalidateQueries('userFlightList');
    },
  });

  return {
    data: mutation.data,
    removeFlightTicket: mutation.mutate,
    isSuccess: mutation.isSuccess,
    error: mutation.error,
  };
};
