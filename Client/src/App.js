import * as React from 'react';
import { Reset } from 'styled-reset';
import { Routes, Route } from 'react-router-dom';
// import Footer from './components/footer';
// import Header from './components/header';
import Questions from './components/Questions/Questions';
import AskQuestion from './components/AskQuestion/AskQuestion';
import UpdateQuestion from './components/UpdateQuestion/UpdateQuestion';
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
                {/* <Header /> */}
                {/* <Footer /> */}
                <Questions />
              </>
            }
          />
          <Route path="/questions/ask" element={<AskQuestion />} />
          <Route path="/questions/update" element={<UpdateQuestion />} />
        </Routes>
      </React.Fragment>
    </div>
  );
}

export default App;
