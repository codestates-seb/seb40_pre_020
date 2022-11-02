import Footer from './footer';
import Header from './header';
import Userprofile from '../Userprofile/Userprofile';
import Leftside from '../Leftside/Leftside';
import styled from 'styled-components';

const Homemain = styled.div`
  display: flex;
  justify-content: center;
  margin-bottom: 10px;
`;
function Mypage() {
  return (
    <div>
      <Header />
      <Homemain>
        <Leftside />
        <Userprofile />
      </Homemain>
      <Footer />
    </div>
  );
}
export default Mypage;
