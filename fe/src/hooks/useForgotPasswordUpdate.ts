import { useMutation } from '@tanstack/react-query';
import { fetchForgotPasswordUpdate } from '../services';

export const useForgotPasswordUpdate = (data) => {
  const mutation = useMutation({
    mutationFn: async () => {
      const result = await fetchForgotPasswordUpdate(data);
      return result;
    },
    retry: false,
  });

  return {
    data: mutation.data,
    forgotPasswordUpdate: mutation.mutate,
    isLoading: mutation.isLoading,
    error: mutation.error,
  };
};
