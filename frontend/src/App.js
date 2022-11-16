import './App.css';
import HomePage from './Home/HomePage';
import 'bootstrap/dist/css/bootstrap.min.css';
import SockJsclient from 'react-stomp'

const SOCKET_URL = 'http://localhost:8080/ws'


function App() {

  const onConnected = () => {
    console.log("connected")
  } 
  const onDisconnect = () => {
    console.log("disconnected")
  } 
  const onMessage = (msg) => {
    console.log(msg)
  }

  return (
    <div className="App">
      <SockJsclient
        url={SOCKET_URL}
        topics={['/topic/queue/connections']}
        onConnect={onConnected}
        onDisconnect={onDisconnect}
        onMessage={(msg) => onMessage(msg)}
      />
      <HomePage />
    </div>
  );
}

export default App;
