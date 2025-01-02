import { useQuery } from '@tanstack/react-query';
import { fetchValidateEmail } from '../services';

export const useValidateEmail = (email) => {
  const query = useQuery({
    queryKey: ['validateEmail', email],
    queryFn: async () => {
      const result = await fetchValidateEmail(email);
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
    isError: query.isError,
    isSuccess: query.isSuccess,
  };
};
