import { instanceGet, instancePost } from '../axios.config';
import uuid4 from 'uuid4';

export const fetchRegister = async (body) => {
  const url = `${import.meta.env.VITE_BASE_URL}/api/v1/auth/register`;
  const res = await instancePost.post(url, body);
  return res.data;
};

export const fetchLogin = async (body) => {
  const url = `${import.meta.env.VITE_BASE_URL}/api/v1/auth/login`;
  const data = {
    ...body,
    requestId: uuid4(),
  };
  const res = await instancePost.post(url, data);
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

export const fetchFlightAll = async () => {
  const requestId = uuid4();
  const url = `${
    import.meta.env.VITE_BASE_URL
  }/api/v1/flights/all?requestId=${requestId}`;
  const res = await instanceGet.get(url);
  return res.data;
};

export const fetchFilterFlight = async (
  departureCity,
  arrivalCity,
  departureDay
) => {
  const requestId = uuid4();
  const url = `${
    import.meta.env.VITE_BASE_URL
  }/api/v1/flights/search?requestId=${requestId}&departureCity=${departureCity}&arrivalCity=${arrivalCity}&departureDay=${departureDay}`;
  const res = await instanceGet.get(url);
  return res.data;
};

export const fetchBuyFlightTicket = async (flightId) => {
  const url = `${import.meta.env.VITE_BASE_URL}/api/v1/flight/sell`;
  const data = {
    flightId,
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

export const fetchAddFlight = async (flight) => {
  const url = `${import.meta.env.VITE_BASE_URL}/api/v1/flight/add`;
  const data = {
    ...flight,
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

export const fetchUserFlightList = async () => {
  const requestId = uuid4();
  const url = `${
    import.meta.env.VITE_BASE_URL
  }/api/v1/flights/list?requestId=${requestId}`;
  const res = await instanceGet.get(url, {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('token')}`,
    },
  });
  return res.data;
};
