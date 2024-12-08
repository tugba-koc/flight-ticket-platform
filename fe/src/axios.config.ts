import axios from 'axios';

const timeoutValue: number = 60000;

export const instanceGet = axios.create({
  headers: {
    'Content-Type': 'application/json;charset=UTF-8',
    Accept: 'application/json;charset=utf-8',
  },
  timeout: timeoutValue,
});

export const instancePost = axios.create({
  headers: { 'Content-Type': 'application/json;charset=UTF-8' },
  timeout: timeoutValue,
});

instancePost.interceptors.request.use(async (config) => {
  return config;
});

instancePost.interceptors.response.use(
  function (response) {
    return response;
  },
  function (error) {
    return Promise.reject(error);
  }
);