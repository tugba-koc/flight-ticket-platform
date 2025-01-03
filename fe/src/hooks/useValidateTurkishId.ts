import { useQuery } from '@tanstack/react-query';
import { fetchValidateTurkishId } from '../services';

export const useValidateTurkishId = (tcId) => {
  const query = useQuery({
    queryKey: ['validateTurkishId', tcId],
    queryFn: async () => {
      const result = await fetchValidateTurkishId(tcId);
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
