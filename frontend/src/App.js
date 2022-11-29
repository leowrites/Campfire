import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import SockJsclient from 'react-stomp';
import ErrorMessage from './ErrorMessage';
import { Outlet } from 'react-router';
import useGlobalContext from './GlobalContext';

const SOCKET_URL = 'http://localhost:8080/ws';

function App() {
  const globalContext = useGlobalContext();

  return (
    <div className='App'>
      <SockJsclient
        url={SOCKET_URL}
        topics={[
          '/users/queue/connections/request',
          '/users/queue/connections/accept',
        ]}
        ref={globalContext.socketRef}
        onConnect={globalContext.onConnected}
        onDisconnect={globalContext.onDisconnect}
        onMessage={globalContext.onMessage}
      />
      <Outlet />
      <ErrorMessage />
    </div>
  );
}

export default App;
