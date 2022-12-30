import React, { useContext, useState, useMemo, useEffect } from 'react';
import axios from 'axios';

interface AuthContext{
  principal: User
}

interface Props {
  children: React.ReactNode
}

export const AuthContext = React.createContext<Partial<AuthContext>>({})

export function AuthContextProvider({ children } : Props) {
  const [principal, setPrincipal] = useState<User>()

  useEffect(() => {
    login();
    // once user is authenticated, get info
  }, []);

  const login = () => {
    axios
      .post('/users/authenticate')
      .then((data) => {
        return {
          principal: data.data.principal, 
          username: data.data.principal.username}
      })
      .then(data => {
        getUserInfo(data)
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const getUserInfo = ({username, principal}: {
    username: string,
    principal: User
  }) => {
    axios
      .get<User>(`/users/${username}`)
      .then((response) => {
        setPrincipal({...principal, ...response.data});
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const memo = useMemo(
    () => ({
      principal,
      setPrincipal,
      getUserInfo,
      login
    }),
    [principal]
  );

  return <AuthContext.Provider value={memo}>{children}</AuthContext.Provider>;
}

export default function useAuthContext() {
  return useContext(AuthContext);
}
