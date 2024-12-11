import { useMutation } from '@tanstack/react-query';
import { fetchLogout } from '../services';

export const useLogout = () => {
  const mutation = useMutation({
    mutationFn: async () => {
      const result = await fetchLogout();
      return result;
    },
    retry: false,
  });

  return {
    data: mutation.data,
    logout: mutation.mutate,
    isLoading: mutation.isLoading,
    error: mutation.error,
    isSuccess: mutation.isSuccess,
  };
};
