import Container from 'react-bootstrap/Container';
import { useEffect, useState } from 'react';
import InternshipPaper from './InternshipPaper';
import Grid from '@mui/material/Grid';
import UserCard from '../Component/UserCard';

function HomePage({ users, sendConnectionRequest }) {
  const [internships, setInternships] = useState([]);
  useEffect(() => {
    const internships = [];
    for (let i = 0; i < 50; i++) {
      internships.push({
        name: 'Apple',
        info: 'Pls give me an internship 🙏 ',
      });
    }
    setInternships(internships);
  }, []);

  return (
    <>
      <Container>
        <Grid container>
          <Grid item xs={6}>
            {internships.map((v, i) => (
              <InternshipPaper key={i} title={v.name} info={v.info} />
            ))}
          </Grid>
          <Grid item xs={6}>
            {users.map((v, i) => (
              <UserCard
                key={i}
                username={v.username}
                name={v.name}
                connections={v.connections}
                incomingConnectionRequests={v.incomingConnectionRequests}
                outgoingConnectionRequests={v.outgoingConnectionRequests}
                sendConnectionRequest={sendConnectionRequest}
              />
            ))}
          </Grid>
        </Grid>
      </Container>
    </>
  );
}

export default HomePage;
