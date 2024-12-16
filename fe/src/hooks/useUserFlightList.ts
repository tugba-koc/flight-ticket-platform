import { useQuery } from '@tanstack/react-query';
import { fetchUserFlightList } from '../services';

export const useUserFlightList = () => {
  const { data, refetch, isLoading, error, isSuccess } = useQuery({
    queryKey: ['useFlightAll'],
    queryFn: async () => {
      const result = await fetchUserFlightList();
      return result;
    },
    enabled: true,
    retry: false,
    staleTime: 1000 * 60 * 5,
    gcTime: 1000 * 60 * 5,
  });

  return { data, refetch, isLoading, error, isSuccess };
};
