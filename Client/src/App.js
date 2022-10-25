import * as React from 'react';
import { Reset } from 'styled-reset';
import { Routes, Route } from 'react-router-dom';
import Footer from './components/footer';
import Header from './components/header';

function App() {
  return (
    <div>
      <React.Fragment>
        <Reset />
        <Routes>
          <Route
            path="/"
            element={
              <>
                <Header />
                <Footer />
              </>
            }
          ></Route>
        </Routes>
      </React.Fragment>
    </div>
  );
}

export default App;
