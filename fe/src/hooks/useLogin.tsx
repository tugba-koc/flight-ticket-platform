import { fetchLogin } from '../services';

export const useLogin = (data) => {
  const mutation = useMutation({
    mutationFn: async () => {
      const result = await fetchLogin(data);
      return result;
    },
    retry: false,
  });

  return {
    data: mutation.data,
    login: mutation.mutate,
    isLoading: mutation.isLoading,
    error: mutation.error,
  };
};
