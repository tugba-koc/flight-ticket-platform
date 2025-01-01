import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import './index.css';
import App from './App.tsx';
import { BrowserRouter } from 'react-router';
import {
  MutationCache,
  QueryCache,
  QueryClient,
  QueryClientProvider,
} from '@tanstack/react-query';
import { UserProvider } from './context/UserContext.tsx';

// global error handling
const queryClient = new QueryClient({
  queryCache: new QueryCache({
    onError: (error) => {
      console.log(error);
      if (error?.status === 403 || error?.status === 401) {
        localStorage.clear();
        window.location.href = '/auth/login';
      }
    },
  }),
  mutationCache: new MutationCache({
    onError: (error) => {
      console.log(error);
      if (error?.status === 403 || error?.status === 401) {
        localStorage.clear();
        window.location.href = '/auth/login';
      }
    },
  }),
});

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <BrowserRouter>
      <QueryClientProvider client={queryClient}>
        <UserProvider>
          <App />
        </UserProvider>
      </QueryClientProvider>
    </BrowserRouter>
  </StrictMode>
);
