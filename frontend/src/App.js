import './App.css';
import HomePage from './Home/HomePage';
import 'bootstrap/dist/css/bootstrap.min.css';
import SockJsclient from 'react-stomp'
import { useEffect, useRef, useState } from 'react';
import Navbar from './Component/Navbar'

const SOCKET_URL = 'http://localhost:8080/ws'

// this is to be replaced once authentication is implemented
const CURRENT_USERNAME = 'leoliu'

function App() {
  const clientRef = useRef()
  // const [sessionId, setSessionId] = useState('')
  const [users, setUsers] = useState([])

  useEffect(() => {
    fetch('/users')
      .then(res => res.json())
      .then(data => setUsers(data))
  }, [])

  const sendMessage = () => {
    clientRef.current.sendMessage(
      ['/app/users/test'],
      JSON.stringify(
        {
          userId: 'leoliu',
          targetId: 'syx'
        }
      )
    )
  }

  const sendConnectionRequest = (username) => {
    console.log('message sent')
    clientRef.current.sendMessage(
      ['/app/users/connections/request'],
      JSON.stringify(
        {
          userId: CURRENT_USERNAME,
          targetId: username
        }
      )
    )
  }

  const onConnected = () => {
    console.log("connected")
  } 
  const onDisconnect = () => {
    console.log("disconnected")
  } 

  // need to have different handlers than this generic one
  const onMessage = (msg) => {
    if (msg.message === "Success") {
      const newUsers = users.map(u => {
        if (u.username === msg.userId) {
          u.connections = msg.connections
        }
        return u
      })
      console.log(newUsers)
      setUsers(newUsers)
    }
    console.log(msg)
  }

  return (
    <div className="App">
      <SockJsclient
        url={SOCKET_URL}
        topics={['/topic/users/connections', '/topic/users/test',
        '/topic/users/connections/request',
        '/users/queue/reply']}
        ref={clientRef}
        onConnect={onConnected}
        onDisconnect={onDisconnect}
        options={{
          // options in https://github.com/sockjs/sockjs-client#sockjs-client-api
          // sessionId: () => "leoliu"
        }}
        onMessage={(msg) => onMessage(msg)}
      />
      <Navbar sendMessage={sendMessage}/>
      <HomePage users={users} sendConnectionRequest={sendConnectionRequest}/>
    </div>
  );
}

function SignUp() {
  return <h2>SIGN UP</h2>;
}

function Login(){
    return <h2> LOGIN </h2>
}


export default App;
