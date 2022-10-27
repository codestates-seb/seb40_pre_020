import Questions from '../Questions/Questions.js';
import Leftmenu from '../Leftside/Leftside.js';
import Rightmenu from '../Rightside/Rightside.js';
import Header from './header.js';
import Footer from './footer.js';
import styled from 'styled-components';

const Homemain = styled.div`
  display: flex;
  justify-content: center;
`;

function Home() {
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
