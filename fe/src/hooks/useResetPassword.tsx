import { useQuery } from '@tanstack/react-query';
import { fetchResetPassword } from '../services';

export const useResetPassword = (formData) => {
  const { data, refetch, isLoading, error } = useQuery({
    queryKey: ['resetPassword', formData],
    queryFn: async () => {
      if (formData) {
        const result = await fetchResetPassword(formData);
        return result;
      } else {
        return { data: null };
      }
    },
    enabled: false,
    retry: false,
    staleTime: 1000 * 60 * 5,
    gcTime: 1000 * 60 * 5,
  });

  return { data, refetch, isLoading, error };
};
