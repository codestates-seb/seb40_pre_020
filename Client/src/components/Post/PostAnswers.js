import VoteBtn from '../VoteBtn/VoteBtn';
import styled from 'styled-components';
import axios from 'axios';

function Post1({ item }) {
  const handleRe = () => {
    axios
      // eslint-disable-next-line no-undef
      .delete(`http://3.39.219.172:8080/answers/${item.id}`)
      .then(() => {
        window.location.reload();
      });
  };
  return (
    <PostAs>
      <PostAss>
        <VoteBtn
          postId1={item.id}
          memberId1={item.memberId}
          postVoteCount1={item.postVoteCount}
        />
        <span>{item.postContent}</span>
        <button onClick={handleRe}>Delete</button>
      </PostAss>
    </PostAs>
  );
}
const PostAss = styled.div`
  display: flex;
  align-items: center;
  padding: 15px;
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
  span {
    display: flex;
    align-items: center;
    margin-left: 30px;
  }
  button {
    margin-left: auto;
  }
`;
const PostAs = styled.div`
  padding: 15px;
  margin-top: 30px;
  h1 {
    margin-bottom: 30px;
    font-size: 20px;
    font-weight: 700;
  }
`;

export default Post1;
