import { useQuery } from '@tanstack/react-query';
import { fetchUserInfo } from '../services';

export const useUserInfo = () => {
  const query = useQuery({
    queryKey: ['userInfo'],
    queryFn: async () => {
      const result = await fetchUserInfo();
      return result;
    },
    enabled: !!localStorage.getItem('token'), // just trigger function when token exists, not component mount
    retry: false,
    staleTime: 1000 * 60 * 5,
    gcTime: 1000 * 60 * 5,
  });

  return {
    data: query.data,
    isLoading: query.isLoading,
    error: query.error,
    refetch: query.refetch,
  };
};
