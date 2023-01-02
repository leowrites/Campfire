import axios from 'axios';
import React, { useEffect, useMemo, useState } from 'react';

interface RetrievedCompanies extends CompanyDetails {
  id: string;
}

interface CompanyContextInterface {
  companies: RetrievedCompanies[]
}

export const CompanyContext = React.createContext<CompanyContextInterface>(
  {} as CompanyContextInterface
);

export function CompanyContextProvider({ children }: { children: React.ReactNode }): JSX.Element {
  const [companies, setCompanies] = useState<RetrievedCompanies[]>([]);
  const fetchCompanies = () => {
    axios.get('/corporates').then((res) => setCompanies(res.data));
  };

  useEffect(() => {
    fetchCompanies();
  }, []);

  const memo = useMemo(
    () => ({
      companies,
    }),
    [companies]
  );
  return <CompanyContext.Provider value={memo}>{children}</CompanyContext.Provider>;
}

export default function useCompanyContext() {
  return React.useContext(CompanyContext);
}
