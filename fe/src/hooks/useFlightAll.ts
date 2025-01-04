import { useQuery } from '@tanstack/react-query';
import { fetchFlightAll } from '../services';

export const useFlightAll = () => {
  const query = useQuery({
    queryKey: ['useFlightAll'],
    queryFn: async () => {
      const result = await fetchFlightAll();
      return result;
    },
    enabled: true,
    retry: false,
    staleTime: 1000 * 60 * 5,
    gcTime: 1000 * 60 * 5,
  });

  return {
    data: query.data,
    refetch: query.refetch,
    isLoading: query.isLoading,
    error: query.error,
    isSuccess: query.isSuccess,
  };
};
