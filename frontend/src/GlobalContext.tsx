import React, { useContext, useState, useRef, useMemo, SyntheticEvent } from 'react';
import useAuthContext from './AuthContext';

interface SocketClientRefInterface {
  sendMessage: (topic: string[], message: string) => void;
}

interface GlobalContext {
  socketRef: React.MutableRefObject<SocketClientRefInterface | undefined>;
  setMsg: React.Dispatch<React.SetStateAction<string>>;
  setShowMsg: React.Dispatch<React.SetStateAction<boolean>>;
  showMsg: boolean;
  msg: string;
  onConnected: () => void;
  onDisconnect: () => void;
  onMessage: (msg: ServerMessageResponse) => void;
  status: string | undefined;
  setStatus: React.Dispatch<React.SetStateAction<string | undefined>>;
  sendAcceptConnectionRequest: (target: string) => void;
  sendConnectionRequest: (target: string) => void;
  handleNotificationClose: (event: React.SyntheticEvent<any> | Event, reason: string) => void;
}

interface ServerMessageResponse {
  serverStatus: string;
  action?: string;
  message: string;
  outgoingConnectionRequests: User[];
  incomingConnectionRequests: User[];
  connectedUsers: User[];
}

enum ConnectionStatus {
  OUTGOING_CONNECTION_REQUEST = 'OUTGOING_CONNECTION_REQUEST',
  INCOMING_CONNECTION_REQUEST = 'INCOMING_CONNECTION_REQUEST',
}

export const GlobalContext = React.createContext<GlobalContext>({} as GlobalContext);

export function GlobalContextProvider({ children }: { children: React.ReactNode }): JSX.Element {
  const socketRef = useRef<SocketClientRefInterface | undefined>(undefined);
  const [showMsg, setShowMsg] = useState(false);
  const [msg, setMsg] = useState('');
  const [status, setStatus] = useState<string | undefined>(undefined);
  const { setPrincipal } = useAuthContext();
  console.log('rerendered');

  const onConnected = () => {
    console.log('connected');
  };
  const onDisconnect = () => {
    console.log('disconnected');
  };

  // need to have different handlers than this generic one
  const onMessage = (msg: ServerMessageResponse) => {
    console.log(msg);
    if (msg.serverStatus === 'success') {
      setMsg(msg.message);
      setShowMsg(true);
      setStatus('success');
    } else {
      if (msg.action === 'OUTGOING_CONNECT_REQUEST') {
        setPrincipal((principal) => {
          if (principal) { 
            principal.outgoingConnectionRequests = msg.outgoingConnectionRequests;
          }
          return principal;
        });
      } else if (msg.action === 'INCOMING_CONNECT_REQUEST') {
        setPrincipal((principal) => {
          if (principal) {
            principal.incomingConnectionRequests = msg.incomingConnectionRequests;
          }
          return principal;
        });
      } else if (
        msg.action === 'INCOMING_CONNECT_ACCEPT' ||
        msg.action === 'OUTGOING_CONNECT_ACCEPT'
      ) {
        console.log(msg);
        setPrincipal((principal) => {
          if (principal) {
            principal.connectedUsers = msg.connectedUsers;
            principal.incomingConnectionRequests = msg.incomingConnectionRequests;
            principal.outgoingConnectionRequests = msg.outgoingConnectionRequests;
          }
          return principal;
        });
      }
      setMsg(msg.message);
      setShowMsg(true);
      setStatus('success');
    }
  };

  const sendAcceptConnectionRequest = (target: string) => {
    socketRef?.current?.sendMessage(
      ['/app/users/connections/accept'],
      JSON.stringify({
        targetId: target,
      })
    );
  };

  const sendConnectionRequest = (target: string) => {
    if (!target) {
      setMsg('Please provide a valid input!');
      setShowMsg(true);
      setStatus('warning');
    } else {
      socketRef?.current?.sendMessage(
        ['/app/users/connections/request'],
        JSON.stringify({
          targetId: target,
        })
      );
    }
  };

  const handleNotificationClose = (event: React.SyntheticEvent<any> | Event, reason: string) => {
    if (reason === 'clickaway') {
      return;
    }
    setShowMsg(false);
    setMsg('');
    setStatus(undefined);
  };

  const memo = useMemo(
    () => ({
      socketRef,
      setMsg,
      showMsg,
      setShowMsg,
      msg,
      onConnected,
      onDisconnect,
      onMessage,
      status,
      setStatus,
      sendAcceptConnectionRequest,
      sendConnectionRequest,
      handleNotificationClose,
    }),
    [msg, onMessage]
  );

  return <GlobalContext.Provider value={memo}>{children}</GlobalContext.Provider>;
}

export default function useGlobalContext() {
  return useContext(GlobalContext);
}
