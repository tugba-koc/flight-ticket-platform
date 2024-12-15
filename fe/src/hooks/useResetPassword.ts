import { useMutation } from '@tanstack/react-query';
import { fetchResetPassword } from '../services';

export const useResetPassword = (data) => {
  const mutation = useMutation({
    mutationFn: async () => {
      const result = await fetchResetPassword(data);
      return result;
    },
    retry: false,
  });

  return {
    data: mutation.data,
    resetPassword: mutation.mutate,
    isLoading: mutation.isLoading,
    error: mutation.error,
  };
};
