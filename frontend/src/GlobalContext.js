import React, { useContext, useState, useRef, useMemo } from 'react';
import useAuthContext from './AuthContext'

export const GlobalContext = React.createContext();

export function GlobalContextProvider({ children }) {
  const socketRef = useRef();
  const [showMsg, setShowMsg] = useState(false);
  const [msg, setMsg] = useState('');
  const [users, setUsers] = useState([]);
  const [status, setStatus] = useState();
  const authContext = useAuthContext()

  const onConnected = () => {
    console.log('connected');
  };
  const onDisconnect = () => {
    console.log('disconnected');
  };

  // need to have different handlers than this generic one
  const onMessage = (msg) => {
    // implement handling this
    console.log(msg);
  };

  const sendAcceptConnectionRequest = (user, target) => {
    console.log('message sent');
    socketRef.current.sendMessage(
      ['/app/users/connections/accept'],
      JSON.stringify({
        userId: user,
        targetId: target,
      })
    );
  };

  const sendConnectionRequest = (user, target) => {
    console.log('message sent');
    console.log(authContext.principal)
    socketRef.current.sendMessage(
      ['/app/users/connections/request'],
      JSON.stringify({
        userId: user,
        targetId: target,
      })
    );
  };

  const handleNotificationClose = (_, reason) => {
    if (reason === 'clickaway') {
      return;
    }
    setShowMsg(false);
    setMsg('');
    setStatus();
  };

  const memo = useMemo(
    () => ({
      socketRef,
      showMsg,
      msg,
      onConnected,
      onDisconnect,
      onMessage,
      status,
      sendAcceptConnectionRequest,
      sendConnectionRequest,
      handleNotificationClose,
    }),
    [users]
  );

  return <GlobalContext.Provider value={memo}>{children}</GlobalContext.Provider>;
}

export default function useGlobalContext() {
  return useContext(GlobalContext);
}
