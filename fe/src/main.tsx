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

// global error handling
const queryClient = new QueryClient({
  queryCache: new QueryCache({
    onError: (error) => {
      if (error?.status === 403) {
        /*         localStorage.clear();
        window.location.href = '/login'; */
      }
    },
  }),
  mutationCache: new MutationCache({
    onError: (error) => {
      if (error?.status === 403) {
        /*         localStorage.clear();
        window.location.href = '/login'; */
      }
    },
  }),
});

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <BrowserRouter>
      <QueryClientProvider client={queryClient}>
        <App />
      </QueryClientProvider>
    </BrowserRouter>
  </StrictMode>
);
