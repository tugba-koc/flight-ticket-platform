import { useQuery } from '@tanstack/react-query';
import { fetchUserInfo } from '../services';

export const useUserInfo = () => {
  const { data, refetch, isLoading, error } = useQuery({
    queryKey: ['userInfo'],
    queryFn: async () => {
      const result = await fetchUserInfo();
      return result;
    },
    enabled: true,
    retry: false,
    staleTime: 1000 * 60 * 5,
    gcTime: 1000 * 60 * 5,
  });

  return { data, refetch, isLoading, error };
};
