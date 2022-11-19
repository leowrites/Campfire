import './App.css';
import HomePage from './Home/HomePage';
import 'bootstrap/dist/css/bootstrap.min.css';
import SockJsclient from 'react-stomp'
import { useEffect, useRef, useState } from 'react';
import Navbar from './Component/Navbar'
import ErrorMessage from './ErrorMessage';

const SOCKET_URL = 'http://localhost:8080/ws'

// this is to be replaced once authentication is implemented
const CURRENT_USERNAME = 'leoliu'

// Have different handlers for different endpoints
// delete option

function App() {
  const clientRef = useRef()
  // const [sessionId, setSessionId] = useState('')
  const [users, setUsers] = useState([])
  const [showMsg, setShowMsg] = useState(false)
  const [msg, setMsg] = useState('')
  const [status, setStatus] = useState()

  useEffect(() => {
    fetch('/users')
      .then(res => res.json())
      .then(data => setUsers(data))
  }, [])

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
    if (msg.serverStatus === "SUCCESS") {
      const newUsers = users.map(u => {
        if (u.username === msg.userId) {
          u.connections = msg.connections
          u.incomingConnectionRequests = msg.incomingConnectionRequests
          u.outgoingConnectionRequests = msg.outgoingConnectionRequests
        }
        return u
      })
      setUsers(newUsers)
      setMsg(msg.message)
      setStatus('success')
    } else {
      setMsg(msg.message)
      setStatus('error')
    }
    setShowMsg(true)
    console.log(msg)
  }

  const handleClose = (event, reason) => {
    if (reason === 'clickaway') {
      return
    }
    setShowMsg(false)
    setMsg('')
    setStatus()
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
      <Navbar />
      <HomePage users={users} sendConnectionRequest={sendConnectionRequest}/>
      <ErrorMessage open={showMsg} handleClose={handleClose} msg={msg} status={status}/>
    </div>
  );
}

export default App;
