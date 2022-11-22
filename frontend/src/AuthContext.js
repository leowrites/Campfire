import React, { useContext, useState, useMemo, useEffect } from 'react';
import axios from 'axios';

export const AuthContext = React.createContext();

export function AuthContextProvider({ children }) {
  const [principal, setPrincipal] = useState();

  useEffect(() => {
    login();
  }, []);

  console.log("rerendered")
  const login = () => {
    axios
      .post('/users/authenticate')
      .then((data) => {
        setPrincipal(data.data.principal);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const memo = useMemo(
    () => ({
      principal,
      setPrincipal
    }),
    [principal]
  );

  return <AuthContext.Provider value={memo}>{children}</AuthContext.Provider>;
}

export default function useAuthContext() {
  return useContext(AuthContext);
}
