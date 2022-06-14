import React from 'react';
import AppProvider from './context/AppProvider'
import MainRoute from './routes/MainRoute';


function App(props) {
  
  return (
    
    <AppProvider>
      <React.StrictMode>
        <MainRoute />
      </React.StrictMode>
    </AppProvider>
  );
}

export default App;
