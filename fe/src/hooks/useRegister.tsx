import { useQuery } from '@tanstack/react-query';
import { fetchRegister } from '../services';

export const useRegister = (formData) => {
  const { data, refetch, isLoading, error } = useQuery({
    queryKey: ['register', formData],
    queryFn: async () => {
      if (formData) {
        const result = await fetchRegister(formData);
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
