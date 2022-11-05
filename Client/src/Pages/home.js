import Questions from '../components/Questions/Questions';
import Leftmenu from '../components/Leftside/Leftside';
import Rightmenu from '../components/Rightside/Rightside';
import Header from './header.js';
import Footer from './footer.js';
import styled from 'styled-components';
import { useState } from 'react';

const Homemain = styled.div`
  display: flex;
  justify-content: center;
`;

function Home() {
  let [tag, setTag] = useState();
  return (
    <div>
      <Header tag={tag} setTag={setTag} />
      <Homemain>
        <Leftmenu />
        <Questions tag={tag} setTag={setTag} />
        <Rightmenu />
      </Homemain>
      <Footer />
    </div>
  );
}

export default Home;
