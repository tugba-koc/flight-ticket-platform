import { instanceGet, instancePost } from '../axios.config';
import uuid4 from 'uuid4';

export const fetchRegister = async (body) => {
  const url = `${import.meta.env.VITE_BASE_URL}/api/v1/auth/register`;
  const res = await instancePost.post(url, body);
  return res.data;
};

export const fetchLogin = async (body) => {
  const url = `${import.meta.env.VITE_BASE_URL}/api/v1/auth/login`;
  const res = await instancePost.post(url, body);
  return res.data;
};

export const fetchResetPassword = async (body) => {
  const url = `${import.meta.env.VITE_BASE_URL}/api/v1/auth/reset-password`;
  const data = {
    password: body.password,
    newPassword: body.confirmPassword,
    requestId: uuid4(),
  };

  const token = localStorage.getItem('token');

  const res = await instancePost.post(url, data, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  return res.data;
};

export const fetchDeposit = async (amount) => {
  const url = `${import.meta.env.VITE_BASE_URL}/api/v1/user/deposit`;
  const data = {
    amount,
    requestId: uuid4(),
  };

  const token = localStorage.getItem('token');

  const res = await instancePost.post(url, data, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return res.data;
};

export const fetchUserInfo = async () => {
  const requestId = uuid4();
  const url = `${
    import.meta.env.VITE_BASE_URL
  }/api/v1/user/info?requestId=${requestId}`;
  const res = await instanceGet.get(url, {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('token')}`,
    },
  });
  return res.data;
};

export const fetchLogout = async () => {
  const url = `${import.meta.env.VITE_BASE_URL}/api/v1/auth/logout`;

  const token = localStorage.getItem('token');

  const res = await instancePost.post(url, undefined, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return res.data;
};
