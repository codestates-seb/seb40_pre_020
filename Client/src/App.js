import * as React from 'react';
import { Reset } from 'styled-reset';
import { Routes, Route } from 'react-router-dom';

function App() {
  return (
    <div>
      <React.Fragment>
        <Reset />
        <Routes>
          <Route></Route>
        </Routes>
      </React.Fragment>
    </div>
  );
}

export default App;
