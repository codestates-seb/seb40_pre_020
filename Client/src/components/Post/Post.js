import VoteBtn from '../VoteBtn/VoteBtn';
import styled from 'styled-components';
import Editor from '../Editor/Editor';
import axios from 'axios';
import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';

const Postmain = styled.div`
  display: flex;
  flex-direction: column;
  border: 1px solid hsl(210, 8%, 85%);
  border-left-width: 1px;
  border-top-width: 0;
  border-bottom-width: 0;
  border-right-width: 0;
  padding: 20px;
`;

const Mainheader = styled.div`
  display: flex;
  justify-content: space-between;
  padding: 15px;
  h1 {
    font-size: 30px;
    font-weight: 600;
  }
  button {
    padding: 10px;
  }
  @media screen and (max-width: 900px) {
    flex-direction: column-reverse;
    & > div {
      height: 35px;
      margin-bottom: 10px;
    }
    button {
      margin-left: auto;
    }
  }
`;

const Subheader = styled.div`
  display: flex;
  border-bottom: 1px solid hsl(210, 8%, 85%);
  span {
    margin-right: 2px;
    padding: 15px;
    color: #777;
  }
`;

const Postm = styled.div`
  display: block;
  padding: 15px;
  margin-top: 30px;
  p {
    color: #777;
    padding: 15px;
    margin-top: 30px;
  }
`;

const SF = styled.div`
  display: flex;
  margin-top: 30px;
  margin-right: auto;
  a {
    font-size: 15px;
    text-decoration: none;
    color: inherit;
    margin-right: 15px;
  }
`;

const PostA = styled.div`
  margin-top: 70px;
  padding: 15px;
  button {
    margin-top: 30px;
    padding: 10px;
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

function Post(props) {
  const [conutent, setContent] = useState();
  // const [limit] = useState(20); //한 페이지 최대 개시물 수
  const [answers, setAnswers] = useState([]);
  const { id } = useParams();
  const handleOnClick = () => {
    const data = {
      parentId: id,
      postTitle: 'Answer1',
      postContent: conutent,
      memberId: 2,
      postView: 0,
      postVoteCount: 0,
      postAnswerCount: 0,
      postCommentCount: 0,
    };
    axios.post('/answers', data).then(() => window.location.reload());
  };

  useEffect(() => {
    axios
      .get(`/answers/${id}?page=1&size=20`)
      .then((res) => setAnswers(res.data.data));
  }, []);

  return (
    <Postmain>
      <Mainheader>
        <h1>{props.userdata.postTitle}</h1>
        <button>Ask Question</button>
      </Mainheader>
      <Subheader>
        <span>View {props.userdata.postView}</span>
        <span>Answer {props.userdata.postAnswerCount}</span>
        <span>Comment {props.userdata.postCommentCount}</span>
      </Subheader>
      <Postm>
        <VoteBtn />
        <p>{props.userdata.postContent}</p>
        <SF>
          <a href="/">Share</a>
          <span>Following</span>
        </SF>
      </Postm>
      <PostAs>
        <div>
          <h1>{answers.length} Answers</h1>
          {answers.map((item, i) => {
            return (
              <PostAss key={i}>
                <VoteBtn />
                <span key={i}>{item.postContent}</span>
              </PostAss>
            );
          })}
        </div>
      </PostAs>
      <PostA>
        <h1>Your Answer</h1>
        <Editor setContent={setContent} style={{ display: 'flex' }} />
        <button onClick={handleOnClick}>Post Your Answer</button>
      </PostA>
    </Postmain>
  );
}

export default Post;
