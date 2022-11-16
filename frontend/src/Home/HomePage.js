import Container from 'react-bootstrap/Container';
import { useEffect, useState } from 'react';
import Navbar from '../Component/Navbar'
import InternshipPaper from './InternshipPaper';

function HomePage() {
  const [internships, setInternships] = useState([]);
  useEffect(() => {
    const internships = []
    for (let i = 0; i < 50; i++){
      internships.push(
        {
          name: "Apple",
          info: "Pls give me an internship 🙏 "
        }
      )
    }
    setInternships(internships)
  }, [])
  return (
    <>
    <Navbar />
    <Container>
      {internships.map((v, i) => (
          <InternshipPaper key={i} title={v.name} info={v.info}/>
      ))}
    </Container>
    </>
  );
}

export default HomePage;
