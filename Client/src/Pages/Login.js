//import React from 'react';
import SocialButtons from '../components/SocialButtons/SocialButtons';
import Footer from './footer';
import styled from 'styled-components';
import LoginForm from '../components/LoginForm/LoginForm';
//import axios from 'axios';
//import { useHistory } from 'react-router-dom';
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
// const { replace } = useHistory();
// const token = "Your_Token_HERE"
// const logincall = () => {
//   axios
//     .post('http://3.39.219.172:8080/v1/signup', {
//       email: email,
//       password: password,
//     })
//     .then((response) => {
//       //handleSuccess
//       console.log('Well done!');
//       console.log(response.data);
//       localStorage.setItem("token", response.data.jwt);
//        replace("/");
//     })
//     .catch((error) => {
//       console.log('an error', error.response);
//     });
// };
//  useEffect(() => {
//    if(localStorage.getItem("token")){
//      replace("/");
//    }
//  },[])
function Login() {
  return (
    <Loginmain>
      <SocialButtons />
      <LoginForm />
      <Footer />
    </Loginmain>
  );
}

export default Login;
