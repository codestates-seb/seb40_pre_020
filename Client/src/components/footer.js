import { Link } from 'react-router-dom';
import styled from 'styled-components';
import { faStackOverflow } from '@fortawesome/free-brands-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

const Logo = styled(Link)`
  padding-top: 30px;
  margin-left: 10px;
  text-decoration: none;
  color: inherit;
`;

const Columnlink = styled(Link)`
  text-decoration: none;
  color: inherit;
`;

const Footermain = styled.footer`
  display: flex;
  flex-flow: row wrap;
  width: 100%;
  bottom: 0;
  background-color: #232629;
  color: #9199a1;
`;

const Footernav = styled.nav`
  display: flex;
  flex: 2 1 auto;
  flex-wrap: wrap;
`;

const Footercolumn = styled.div`
  padding: 30px;
  flex: 1 0 auto;
  font-size: 15px;
`;

const Footerli = styled.li`
  font-size: 12px;
`;

const Footerli2 = styled.li`
  padding-top: 4px;
`;

const Footercopy = styled.div`
  display: flex;
  flex: 1 1 10px;
  flex-direction: column;
  box-sizing: border-box;
  font-size: 12px;
  padding-top: 30px;
  margin-left: 10px;
  p {
    margin-top: auto;
    margin-bottom: 24px;
  }
`;

const H5 = styled.h5`
  margin-bottom: 10px;
  font-weight: 700;
`;

const Footer = () => {
  return (
    <Footermain>
      <Logo to="/">
        <FontAwesomeIcon icon={faStackOverflow} size="2x" />
      </Logo>
      <Footernav>
        <Footercolumn>
          <H5>
            <Columnlink to="/">STACK OVERFLOW</Columnlink>
          </H5>
          <ul>
            <Footerli2>
              <Columnlink to="/">Questions</Columnlink>
            </Footerli2>
            <Footerli2>
              <Columnlink to="/">Help</Columnlink>
            </Footerli2>
          </ul>
        </Footercolumn>
        <Footercolumn>
          <H5>PRODUCTS</H5>
          <ul>
            <Footerli>
              <Footerli2>Teams</Footerli2>
              <Footerli2>Advertising</Footerli2>
              <Footerli2>Collectives</Footerli2>
              <Footerli2>Talent</Footerli2>
            </Footerli>
          </ul>
        </Footercolumn>
        <Footercolumn>
          <H5>COMPANY</H5>
          <ul>
            <Footerli>
              <Footerli2>About</Footerli2>
              <Footerli2>Press</Footerli2>
              <Footerli2>Work Here</Footerli2>
              <Footerli2>Legal</Footerli2>
              <Footerli2>Privacy Policy</Footerli2>
              <Footerli2>Terms of Service</Footerli2>
              <Footerli2>Contact Us</Footerli2>
              <Footerli2>Cookie Settings</Footerli2>
              <Footerli2>Cookie Policy</Footerli2>
            </Footerli>
          </ul>
        </Footercolumn>
        <Footercolumn>
          <H5>STACK EXCHANGE NETWORK</H5>
          <ul>
            <Footerli>
              <Footerli2>Technology</Footerli2>
              <Footerli2>Culture & recreation</Footerli2>
              <Footerli2>Life & arts</Footerli2>
              <Footerli2>Science</Footerli2>
              <Footerli2>Professional</Footerli2>
              <Footerli2>Business</Footerli2>
              <Footerli2>API</Footerli2>
              <Footerli2>Data</Footerli2>
            </Footerli>
          </ul>
        </Footercolumn>
      </Footernav>
      <Footercopy>
        <ul>
          <Footerli>
            <Footerli2>Blog</Footerli2>
            <Footerli2>Facebook</Footerli2>
            <Footerli2>Twitter</Footerli2>
            <Footerli2>LinkedIn</Footerli2>
            <Footerli2>Instagram</Footerli2>
          </Footerli>
        </ul>
        <p>
          Site design / logo © 2022 Stack Exchange Inc; user contributions
          licensed under CC BY-SA. <span>rev 2022.8.31.42952</span>
        </p>
      </Footercopy>
    </Footermain>
  );
};

export default Footer;
