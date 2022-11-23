import React, { useContext, useState, useRef, useMemo } from 'react';

export const GlobalContext = React.createContext();

export function GlobalContextProvider({ children }) {
  const socketRef = useRef();
  const [showMsg, setShowMsg] = useState(false);
  const [msg, setMsg] = useState('');
  const [status, setStatus] = useState();

  const onConnected = () => {
    console.log('connected');
  };
  const onDisconnect = () => {
    console.log('disconnected');
  };

  // need to have different handlers than this generic one
  const onMessage = (msg) => {
    // implement handling this
    if (msg.serverStatus === 'ERROR') {
      setMsg(msg.message);
      setShowMsg(true);
      setStatus('error');
    } else {
      if (msg.action === 'OUTGOING_CONNECTION_REQUEST') {
        // setPrincipal()
      } else if (msg.action === 'INCOMING_CONNECTION_REQUEST') {
      } else if (msg.action === 'INCOMING_CONNECT_ACCEPT') {
      } else if (msg.action === 'OUTGOING_CONNECT_ACCEPT') {
      }
      setMsg(msg.message);
      setShowMsg(true);
      setStatus('success');
    }
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
    if (!target || !user) {
      setMsg('Please provide a valid input!');
      setShowMsg(true);
      setStatus('warning');
    } else {
      console.log(`Connection request sent from ${user} to ${target}`);
      socketRef.current.sendMessage(
        ['/app/users/connections/request'],
        JSON.stringify({
          userId: user,
          targetId: target,
        })
      );
    }
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
    [msg]
  );

  return <GlobalContext.Provider value={memo}>{children}</GlobalContext.Provider>;
}

export default function useGlobalContext() {
  return useContext(GlobalContext);
}
