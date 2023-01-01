import React, { useContext, useState, useMemo, useEffect } from 'react';
import axios from 'axios';

interface AuthContextInterface {
  principal: User | undefined;
  setPrincipal: React.Dispatch<React.SetStateAction<User | undefined>>;
  login: () => void;
}

interface Props {
  children: React.ReactNode;
}

export const AuthContext = React.createContext<AuthContextInterface>({} as AuthContextInterface);

export function AuthContextProvider({ children }: Props): JSX.Element {
  const [principal, setPrincipal] = useState<User | undefined>(undefined);

  useEffect(() => {
    login();
    // once user is authenticated, get info
  }, []);

  const login = () => {
    axios
      .post<User>('/users/authenticate')
      .then((response) => {
        if (!response.data.username) return;
        setPrincipal(response.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const memo = useMemo(
    () => ({
      principal,
      setPrincipal,
      login,
    }),
    [principal]
  );

  return <AuthContext.Provider value={memo}>{children}</AuthContext.Provider>;
}

export default function useAuthContext() {
  return useContext(AuthContext);
}
