import { instancePost } from '../axios.config';

export const fetchRegister = async (body) => {
  const url = `${import.meta.env.VITE_BASE_URL}/api/v1/register`;
  const res = await instancePost.post(url, body);
  return res.data;
};

export const fetchLogin = async (body) => {
  const url = `${import.meta.env.VITE_BASE_URL}/api/v1/login`;
  const res = await instancePost.post(url, body);
  return res.data;
};
