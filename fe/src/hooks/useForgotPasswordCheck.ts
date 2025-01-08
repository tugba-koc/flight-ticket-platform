import { useQuery } from '@tanstack/react-query';
import { fetchForgotPasswordCheck } from '../services';

export const useForgotPasswordCheck = ({ email, phoneNumber, birthDate }) => {
  const query = useQuery({
    queryKey: ['forgotPasswordCheck', email, phoneNumber, birthDate],
    queryFn: async () => {
      const result = await fetchForgotPasswordCheck(
        email,
        phoneNumber,
        birthDate
      );
      return result;
    },
    enabled: false, // just trigger refetch, not component mount
    retry: false,
    staleTime: 1000 * 60 * 5,
    gcTime: 1000 * 60 * 5,
  });

  return {
    data: query.data,
    forgotPasswordCheck: query.refetch,
    isLoading: query.isLoading,
    error: query.error,
    isSuccess: query.isSuccess,
  };
};
