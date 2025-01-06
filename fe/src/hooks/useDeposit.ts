import { useMutation } from '@tanstack/react-query';
import { fetchDeposit } from '../services';

export const useDeposit = (amount, userInfoFetch) => {
  const mutation = useMutation({
    mutationFn: async () => {
      const result = await fetchDeposit(amount);
      return result;
    },
    retry: false,
    onSuccess: () => {
      userInfoFetch();
    },
  });

  return {
    data: mutation.data,
    deposit: mutation.mutate,
    isLoading: mutation.isLoading,
    error: mutation.error,
  };
};
