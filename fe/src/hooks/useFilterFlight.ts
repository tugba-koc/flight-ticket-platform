import { useQuery } from '@tanstack/react-query';
import { fetchFilterFlight } from '../services';

export const useFilterFlight = ({
  departureCity,
  arrivalCity,
  departureDay,
}) => {
  const query = useQuery({
    queryKey: ['filterFlight', departureCity, arrivalCity, departureDay],
    queryFn: async () => {
      const result = await fetchFilterFlight(
        departureCity,
        arrivalCity,
        departureDay
      );
      return result;
    },
    enabled: false, // just trigger refetch, not component mount
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
