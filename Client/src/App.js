import * as React from 'react';
import { Reset } from 'styled-reset';
import { Routes, Route } from 'react-router-dom';
import Home from './components/Pages/home';
import Tags from './components/Pages/tags';
import AskQuestion from './components/AskQuestion/AskQuestion';
import UpdateQuestion from './components/UpdateQuestion/UpdateQuestion';
import Mypage from './components/Pages/mypage';
function App() {
  return (
    <div>
      <React.Fragment>
        <Reset />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/tags" element={<Tags />}></Route>
          {/* <Route
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
          ></Route> */}
          <Route path="/questions/ask" element={<AskQuestion />} />
          <Route path="/mypage" element={<Mypage />} />
          <Route path="/questions/update" element={<UpdateQuestion />} />
        </Routes>
      </React.Fragment>
    </div>
  );
}

export default App;
