import React, { createContext, useReducer, useContext } from 'react';
import { userInitialState, userReducer } from '../reducer/userReducer';

interface UserContextType {
  state: typeof userInitialState;
  dispatch: React.Dispatch<any>;
}

const UserContext = createContext<UserContextType | undefined>(undefined);

interface UserProviderProps {
  children: React.ReactNode;
}

export const UserProvider: React.FC<UserProviderProps> = ({ children }) => {
  const [state, dispatch] = useReducer(userReducer, userInitialState);

  return (
    <UserContext.Provider value={{ state, dispatch }}>
      {children}
    </UserContext.Provider>
  );
};

export const useUser = () => useContext(UserContext);
