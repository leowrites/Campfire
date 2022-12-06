import InternshipCard from './InternshipCard';
import Box from '@mui/material/Box';
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
export default function InternshipGroup() {
  const { corporateId } = useParams();
  const [internships, setInternships] = useState([]);
  useEffect(() => {
    axios.get(`/corporates/${corporateId}/internships`).then((data) => {
      console.log('internships', internships);
      setInternships(data.data);
    });
  }, []);
  return (
    <Box sx={{ mt: 5 }}>
      {internships?.map((internship) => (
        <InternshipCard key={internship.id} id={internship.id} jobTitle={internship.jobTitle} />
      ))}
    </Box>
  );
}
