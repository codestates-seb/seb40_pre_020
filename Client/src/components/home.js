import styled from 'styled-components';
import Questions from '../components/Questions/Questions.js';
import RelatedTags from '../components/RelatedTags/RelatedTags.js';
import { Link } from 'react-router-dom';

const Homemain = styled.div`
  display: flex;
  justify-content: center;
`;

const Leftside = styled.div`
  margin-top: 130px;
`;

const Rightside = styled.div`
  margin-top: 200px;
  padding: 30px;
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

function Home() {
  return (
    <div>
      <Homemain>
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
        <Questions />
        <Rightside>
          <RelatedTags />
        </Rightside>
      </Homemain>
    </div>
  );
}

export default Home;
