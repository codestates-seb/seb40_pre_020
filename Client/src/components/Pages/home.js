import Questions from '../Questions/Questions.js';
import Leftmenu from '../Leftside/Leftside.js';
import Rightmenu from '../Rightside/Rightside.js';
import Header from './header.js';
import Footer from './footer.js';
import styled from 'styled-components';
import axios from 'axios';
import { useEffect } from 'react';
const Homemain = styled.div`
  display: flex;
  justify-content: center;
`;

function Home() {
  const ac = () => {
    axios
      .get('api/hello')
      // .then((res) => console.log(res.data))
      .catch((er) => {
        console.log(er);
      });
  };
  useEffect(() => {
    ac();
  }, []);
  return (
    <div>
      <Header />
      <Homemain>
        <Leftmenu />
        <Questions />
        <Rightmenu />
      </Homemain>
      <Footer />
    </div>
  );
}

export default Home;
