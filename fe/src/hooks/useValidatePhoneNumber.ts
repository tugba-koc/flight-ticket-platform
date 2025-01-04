import { useQuery } from '@tanstack/react-query';
import { fetchValidatePhoneNumber } from '../services';

export const useValidatePhoneNumber = (phoneNumber) => {
  const query = useQuery({
    queryKey: ['validatePhoneNumber', phoneNumber],
    queryFn: async () => {
      const result = await fetchValidatePhoneNumber(phoneNumber);
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
