import React, { useContext, useState, useMemo, useEffect } from 'react';
import axios from 'axios';

export const AuthContext = React.createContext();

export function AuthContextProvider({ children }) {
  const [principal, setPrincipal] = useState();

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

  const getUserInfo = ({username, principal}) => {
    axios
      .get(`/users/${username}`)
      .then((data) => {
        setPrincipal({...principal, user: data.data});
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const memo = useMemo(
    () => ({
      principal,
      setPrincipal,
      getUserInfo
    }),
    [principal]
  );

  return <AuthContext.Provider value={memo}>{children}</AuthContext.Provider>;
}

export default function useAuthContext() {
  return useContext(AuthContext);
}
