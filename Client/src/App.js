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
import Posts from './Pages/posts';
import Mypage from './Pages/Mypage';
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
          <Route path="/signup" element={<SignUp />} />
          <Route path="/posts/:id" element={<Posts />} />
          <Route path="/questions/ask" element={<AskQuestion />} />
          <Route path="/questions/update/:id" element={<UpdateQuestion />} />
          <Route path="/mypage/:id" element={<Mypage />} />
        </Routes>
      </React.Fragment>
    </div>
  );
}

export default App;
