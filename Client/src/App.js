import * as React from 'react';
import { Reset } from 'styled-reset';
import { Routes, Route } from 'react-router-dom';
import Footer from './components/footer';
import Header from './components/header';
import Home from './components/home';
import AskQuestion from './components/AskQuestion/AskQuestion';
import UpdateQuestion from './components/UpdateQuestion/UpdateQuestion';
import Userprofile from './components/Userprofile/Userprofile';
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
          <Route path="/questions/update" element={<UpdateQuestion />} />
          <Route
            path="/Userprofile"
            element={
              <>
                <Header />
                <Userprofile />
                <Footer />
              </>
            }
          />
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
