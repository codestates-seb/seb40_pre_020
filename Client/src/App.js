import './App.css';
import React, { useEffect } from "react";
import { Routes, Route, useLocation, Navigate } from "react-router-dom";
import styled from "styled-components";
import axios from "axios";
import { Reset } from 'styled-reset';


function App() {

  return (
    <>
      <Reset/>
      <GlobalStyles />
      <Router>
        <UserContext.Provider value={{user, checkAuth, editUser}}>
          <Header />
        </UserContext.Provider>
      </Router>
    </>

  );
}

export default App;
