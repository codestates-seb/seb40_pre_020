import VoteBtn from '../VoteBtn/VoteBtn';
import styled from 'styled-components';
import axios from 'axios';
import { useEffect, useState } from 'react';

function Post1({ item }) {
  const [count, setCount] = useState(0);
  const handleRe = () => {
    axios.delete(`/answers/${item.id}`).then(() => {
      setCount(count + 1);
    });
  };
  useEffect(() => {}, [count]);
  return (
    <PostAs>
      <PostAss>
        <VoteBtn
          postId1={item.id}
          memberId1={item.memberId}
          postVoteCount1={item.postVoteCount}
        />
        <span>
          {item.postContent}
          {count}
        </span>
        <button onClick={handleRe}>삭제</button>
      </PostAss>
    </PostAs>
  );
}
const PostAss = styled.div`
  display: flex;
  padding: 15px;
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
  span {
    display: flex;
    align-items: center;
    margin-left: 30px;
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
