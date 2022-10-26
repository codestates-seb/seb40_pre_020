import * as React from 'react';
import { Reset } from 'styled-reset';
import { Routes, Route } from 'react-router-dom';
import Footer from './components/footer';
import Header from './components/header';
import Home from './components/home';
import AskQuestion from './components/AskQuestion/AskQuestion';
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
                <Home />
                <Footer />
              </>
            }
          />
          <Route path="/questions/ask" element={<AskQuestion />} />
          <Route
            path="/tags"
            element={
              <>
                <Header />
                <Footer />
              </>
            }
          ></Route>
          <Route
            path="/users"
            element={
              <>
                <Header />
                <Footer />
              </>
            }
          ></Route>
          <Route
            path="/companies"
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
