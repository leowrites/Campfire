import useAuthContext from './AuthContext';
import { useNavigate } from 'react-router';
import { useEffect } from 'react';
import axios from 'axios';

export default function RouteProtectionWrapper({ children }) {
  const { principal, getUserInfo } = useAuthContext();
  const navigate = useNavigate();
  useEffect(() => {
    if (!principal) {
      axios
        .post('/users/authenticate')
        .then((data) => {
          return {
            principal: data.data.principal,
            username: data.data.principal.username,
          };
        })
        .then((data) => {
          getUserInfo(data);
        })
        .catch((err) => {
          navigate('/login');
        });
    }
  }, []);
  return principal ? children : null;
}
