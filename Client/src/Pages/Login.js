//import React from 'react';
import SocialButtons from '../components/SocialButtons/SocialButtons';
import Footer from './footer';
import styled from 'styled-components';

const Loginmain = styled.div`
  background-color: #f1f2f3;
  width: 100%;
  height: calc(100vh - 50px);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 24px 0;
`;

function Login() {
  return (
    <Loginmain>
      <SocialButtons />
      <Footer />
    </Loginmain>
  );
}

export default Login;
