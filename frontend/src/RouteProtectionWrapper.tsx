import useAuthContext from './AuthContext';
import { useNavigate } from 'react-router';
import { useEffect } from 'react';
import axios from 'axios';

export default function RouteProtectionWrapper({ children }: { children: JSX.Element }): JSX.Element {
  const { principal, setPrincipal } = useAuthContext();
  const navigate = useNavigate();
  useEffect(() => {
    if (!principal) {
      axios
        .post('/users/authenticate')
        .then((response) => {
          if (!response.data.username) {
            navigate('/login');
          }
          setPrincipal(response.data);
        })
        .catch((err) => {
          console.log(err);
        });
    }
  }, []);
  return children
}
