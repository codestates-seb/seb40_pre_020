import * as React from 'react';
import { Reset } from 'styled-reset';
import { Routes, Route } from 'react-router-dom';
import Home from './Pages/home';
import Tags from './Pages/tags';
import Users from './Pages/Users';
import AskQuestion from './components/AskQuestion/AskQuestion';
import UpdateQuestion from './components/UpdateQuestion/UpdateQuestion';
import Login from './Pages/Login';
import SignUp from './Pages/SignUp';
function App() {
  return (
    <div>
      <React.Fragment>
        <Reset />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/tags" element={<Tags />} />
          <Route path="/users" element={<Users />} />
          <Route path="/login" element={<Login />} />
          <Route path="/singup" element={<SignUp />} />
          <Route path="/questions/ask" element={<AskQuestion />} />
          <Route path="/questions/update" element={<UpdateQuestion />} />
        </Routes>
      </React.Fragment>
    </div>
  );
}

export default App;
