import { useMutation } from '@tanstack/react-query';
import { fetchRegister } from '../services';

export const useRegister = (data) => {
  const mutation = useMutation({
    mutationFn: async () => {
      const result = await fetchRegister(data);
      return result;
    },
    retry: false,
  });

  return {
    data: mutation.data,
    register: mutation.mutate,
    isLoading: mutation.isLoading,
    error: mutation.error,
  };
};
