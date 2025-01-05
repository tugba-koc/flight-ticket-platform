import { useQuery } from '@tanstack/react-query';
import { fetchUserFlightList } from '../services';

export const useUserFlightList = () => {
  // TODO: learn all the options of useQuery
  const query = useQuery({
    queryKey: ['userFlightList', localStorage.getItem('token')],
    queryFn: async () => {
      const result = await fetchUserFlightList();
      return result;
    },
    enabled: !!localStorage.getItem('token'),
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
