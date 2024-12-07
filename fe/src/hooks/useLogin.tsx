import { useQuery } from '@tanstack/react-query';
import { fetchLogin } from '../services';

export const useLogin = (formData) => {
  const { data, refetch, isLoading, error } = useQuery({
    queryKey: ['login', formData],
    queryFn: async () => {
      if (formData) {
        const result = await fetchLogin(formData);
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
