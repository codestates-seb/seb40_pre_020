// eslint-disable-next-line import/default
import styled from 'styled-components';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { faStackOverflow } from '@fortawesome/free-brands-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useState } from 'react';
import Logout from './Logout';
import { storge } from '../utils/store';

const Navmain = styled.div`
  display: flex;
  justify-content: center;
  max-width: 100%;
  width: 100%;
  margin: 0 auto;
  background-color: #f8f9f9;
  border-top: 3px solid #f48225;
  box-shadow: 0 0 5px 3px rgba(0, 0, 0, 0.5);
  position: fixed;
  z-index: 999;
`;

const Logo = styled(Link)`
  display: flex;
  text-decoration: none;
  color: inherit;
  height: 50px;
  align-items: center;
  padding: 0 15px;
  cursor: pointer;
  span {
    margin-left: 5px;
    margin-top: 5px;
    font-size: 1.5rem;
  }
  b {
    font-weight: 900;
  }
`;

const Ul = styled.ul`
  display: flex;
  padding: 20px;
  text-decoration: none;
  color: inherit;
`;

const Li = styled.li`
  margin-left: 12px;
  :hover {
    background-color: #999;
    border-radius: 20px;
  }
`;

const LogoMain = styled.div`
  margin-top: 30px;
`;

const Input = styled.input`
  background: none;
  border: 1px solid #777;
  border-radius: 5px;
  display: block;
  width: 100%;
  min-width: 450px;
  box-sizing: border-box;
  padding: 10px;
  margin-top: 25px;
  margin-bottom: 20px;
  outline-style: none;
`;

const Button = styled.div`
  display: flex;
  padding: 20px;
`;

const Loginbutton = styled.button`
  background-color: #e1ecf4;
  color: #39739d;
  border: 1px solid #39739d;
  border-radius: 4px;
  :hover {
    background-color: #b3d3ea;
  }
`;

const Signupbutton = styled.button`
  margin-left: 7px;
  background-color: #0a95ff;
  color: #39739d;
  border: 1px solid #39739d;
  border-radius: 4px;
  :hover {
    background-color: #0074cc;
  }
`;

function Header({ setTag }) {
  const navigate = useNavigate();
  const [keyword, Setkeyword] = useState('');
  const [a, seta] = useState('');

  const valueChange = (e) => {
    Setkeyword(e.target.value);
    console.log(keyword);
  };
  const Submit = async (e) => {
    e.preventDefault();

    const res = await axios.get(
      // eslint-disable-next-line no-undef
      process.env.REACT_APP_DB_HOST + '/profiles/1/posts?page=1&size=20',
      {
        params: keyword,
      }
    );
    console.log(res);
    Setkeyword('');
  };
  const handleOnKeyPress = (e) => {
    if (e.key === 'Enter') {
      setTag(a);
      seta('');
      navigate('/');
    }
  };
  return (
    <div>
      <Navmain>
        <Logo to={'/'}>
          <LogoMain>
            <FontAwesomeIcon icon={faStackOverflow} size="2x" />
            <span>
              stack <b>overflow</b>
            </span>
          </LogoMain>
        </Logo>
        <Ul>
          <Li>
            <Logo to={'/'}>About</Logo>
          </Li>
          <Li>
            <Logo to={'/'}>Products</Logo>
          </Li>
          <Li>
            <Logo to={'/'}>For Teams</Logo>
          </Li>
        </Ul>
        <form onChange={valueChange} onSubmit={Submit}>
          <label htmlFor="search">
            <Input
              type="text"
              placeholder="Search"
              value={a}
              onKeyPress={handleOnKeyPress}
              onChange={(e) => {
                seta(e.target.value);
              }}
            />
          </label>
        </form>
        <Button>
          {storge.getData() ? (
            <Logout />
          ) : (
            <>
              <Loginbutton onClick={() => navigate('/login')}>
                Log in
              </Loginbutton>
              <Signupbutton onClick={() => navigate('/signup')}>
                Sign up
              </Signupbutton>
            </>
          )}
        </Button>
      </Navmain>
    </div>
  );
}

export default Header;
