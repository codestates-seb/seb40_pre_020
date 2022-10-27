import { Link } from 'react-router-dom';
import styled from 'styled-components';
const Leftside = styled.div`
  margin-top: 130px;
`;

const Ul = styled.ul`
  padding: 15px;
  margin-top: 15px;
`;

const Leftlink = styled(Link)`
  text-decoration: none;
  color: inherit;
  padding-right: 30px;
`;

const Li = styled.li`
  margin: 30px 0px 0px 30px;
  text-align: center;
`;

function Leftmenu() {
  return (
    <Leftside>
      <Ul>
        <Leftlink to="/">Home</Leftlink>
      </Ul>
      <Ul>
        <p>PUBLIC</p>
        <Li>
          <Leftlink to="/">Qurestions</Leftlink>
        </Li>
        <Li>
          <Leftlink to="/tags">Tags</Leftlink>
        </Li>
        <Li>
          <Leftlink to="/users">Users</Leftlink>
        </Li>
        <Li>
          <Leftlink to="/companies">Companies</Leftlink>
        </Li>
      </Ul>
    </Leftside>
  );
}

export default Leftmenu;
