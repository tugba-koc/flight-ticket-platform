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

instanceGet.interceptors.request.use(async (config) => {
  return config;
});

instanceGet.interceptors.response.use(
  function (response) {
    return response;
  },
  function (error) {
    const standardError = error?.response?.data;
    return Promise.reject(standardError);
  }
);

instancePost.interceptors.request.use(async (config) => {
  return config;
});

instancePost.interceptors.response.use(
  function (response) {
    return response;
  },
  function (error) {
    const standardError = error?.response?.data;
    return Promise.reject(standardError);
  }
);
