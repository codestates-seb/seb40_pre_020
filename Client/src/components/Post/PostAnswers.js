import VoteBtn from '../VoteBtn/VoteBtn';
import styled from 'styled-components';
import axios from 'axios';
// import { useParams } from 'react-router-dom';

function PostAnswers({ item }) {
  // const { id } = useParams();
  const handleRe = () => {
    axios
      .delete(process.env.REACT_APP_DB_HOST + `/answers/${item.id}`)
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
  padding: 15px;
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
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
  h1 {
    margin-bottom: 30px;
    font-size: 20px;
    font-weight: 700;
  }
`;

export default PostAnswers;