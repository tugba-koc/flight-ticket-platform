import { useMutation } from '@tanstack/react-query';
import { fetchAddFlight } from '../services';

export const useAddFlight = (flight) => {
  const mutation = useMutation({
    mutationFn: async () => {
      const result = await fetchAddFlight(flight);
      return result;
    },
    retry: false,
  });

  return {
    data: mutation.data,
    addFlight: mutation.mutate,
    isLoading: mutation.isLoading,
    error: mutation.error,
  };
};
