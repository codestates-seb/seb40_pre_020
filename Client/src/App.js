import * as React from 'react';
import { Reset } from 'styled-reset';
import { Routes, Route } from 'react-router-dom';
import Questions from './components/Questions/Questions';
import AskQuestion from './components/AskQuestion/AskQuestion';
function App() {
  return (
    <div>
      <React.Fragment>
        <Reset />
        <Routes>
          <Route path="/Home" element={<Questions />} />
          <Route path="/questions/ask" element={<AskQuestion />} />
        </Routes>
      </React.Fragment>
    </div>
  );
}

export default App;
