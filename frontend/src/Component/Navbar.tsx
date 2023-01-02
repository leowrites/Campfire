import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import { Box } from '@mui/system';
import { useNavigate } from 'react-router';
import { Link } from 'react-router-dom';
import useAuthContext from '../AuthContext';
import axios from 'axios';
import { ReactComponent as Logo } from './campfire.svg';
import './fonts.css';
import { ReactComponent as ConnectIcon } from './connectIcon.svg';
import { useState } from 'react';
import ConnectPanel from '../Home/ConnectPanel';
import { Drawer } from '@mui/material';
import { useTheme } from '@mui/material';
import CustomTextField from './CustomTextfield';
import Autocomplete from '@mui/material/Autocomplete';
import useCompanyContext from '../global/CompanyContext';

export default function Navbar() {
  const navigate = useNavigate();
  const { principal, setPrincipal } = useAuthContext();
  const theme = useTheme();
  const { companies } = useCompanyContext();
  const [searchInput, setSearchInput] = useState<string>('');
  const [searchSelectedCompany, setSearchSelectedCompany] = useState<string | null>(
    companies?.[0]?.companyName
  );
  const handleLogout = () => {
    setPrincipal(undefined);
    axios
      .post('/logout')
      .then((result) => {
        window.location.reload();
      })
      .catch((err) => {});
  };

  const [open, setOpen] = useState(false);
  const handleNavigateToCompany = () => {
    if (searchSelectedCompany) {
      window.location.href = `/corporates/${
        companies.find((c) => c.companyName === searchSelectedCompany)?.id
      }`;
    }
  };

  return (
    <>
      <AppBar
        position='static'
        style={{ minHeight: '4rem', maxHeight: 'fit-content' }}
        elevation={0}>
        <Toolbar>
          <Link to='/' style={{ textDecoration: 'none' }}>
            <Box sx={{ display: 'flex', alignItems: 'center' }}>
              <Logo style={{ height: 60, padding: 12, ml: 2 }} />
              <Typography
                variant='h5'
                sx={{
                  color: '#F6F2F2',
                  fontWeight: 'bold',
                }}>
                CAMP
              </Typography>
              <Typography
                variant='h5'
                sx={{
                  color: '#ff5634',
                  fontWeight: 'bold',
                  ml: 1,
                }}>
                FIRE
              </Typography>
            </Box>
          </Link>
          <Autocomplete
            id='search-company-navbar'
            options={companies.map((company) => company.companyName)}
            value={searchSelectedCompany}
            onChange={(_, value) => {
              setSearchSelectedCompany(value);
            }}
            onInputChange={(_, value) => {
              setSearchInput(value);
            }}
            renderInput={(params) => (
              <CustomTextField
                {...params}
                hiddenLabel
                size='small'
                placeholder='Search For A Company'
                sx={{ width: '20rem', ml: 'auto', background: '#666666' }}
              />
            )}
          />
          <Button onClick={handleNavigateToCompany} sx={{ color: 'white' }}>
            <Typography>Go</Typography>
          </Button>
          <Box sx={{ ml: 'auto' }}>
            {principal ? (
              <Box sx={{ display: 'inline-flex', alignItems: 'center' }}>
                <Button onClick={() => setOpen(true)}>
                  <ConnectIcon style={{ height: 'auto', width: 60 }}></ConnectIcon>
                </Button>
                <Typography sx={{ color: 'white', fontWeight: 'bold' }}>
                  {principal.username}
                </Typography>
                <Button onClick={handleLogout}>
                  <Typography sx={{ fontWeight: 'bold', color: 'white', textTransform: 'none' }}>
                    Logout
                  </Typography>
                </Button>
              </Box>
            ) : (
              <>
                <Button onClick={() => navigate('login')}>
                  <Typography
                    sx={{ color: theme.palette.secondary.main, fontWeight: 'bold' }}
                    variant='h6'>
                    Login
                  </Typography>
                </Button>
                <Button onClick={() => navigate('signup')}>
                  <Typography sx={{ color: '#ff5634', fontWeight: 'bold' }} variant='h6'>
                    Sign up
                  </Typography>
                </Button>
              </>
            )}
          </Box>
        </Toolbar>
      </AppBar>
      <Drawer open={open} anchor={'right'} onClose={() => setOpen(false)}>
        <ConnectPanel />
      </Drawer>
    </>
  );
}
