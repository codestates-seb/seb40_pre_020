import Questions from '../components/Questions/Questions';
import Leftmenu from '../components/Leftside/Leftside';
import Rightmenu from '../components/Rightside/Rightside';
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
