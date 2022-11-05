import VoteBtn from '../VoteBtn/VoteBtn';
import styled from 'styled-components';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import { useEffect, useState } from 'react';
import Editor from '../Editor/editor';

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
const Btn = styled.button`
  color: black;
  background-color: white;
  font-size: 15px;
  color: inherit;
  margin-right: 15px;
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

const ED = styled.div`
  margin-left: auto;
`;

function Post(props) {
  const navigate = useNavigate();
  const [answers, setAnswers] = useState([]);
  const [content, setContent] = useState();
  const [count, setCount] = useState(0);
  const handleOnClickask = () => navigate(`/questions/ask`);
  const { id } = useParams();
  const handleRemove = () => {
    axios.delete(`/posts/${id}`).then(() => navigate(`/`));
  };

  const handleEdit = () => {
    navigate(`/questions/update/${id}`);
  };
  useEffect(() => {
    axios
      .get(`/answers/${id}?page=1&size=20`)
      .then((res) => setAnswers(res.data.data))
      .then(() => console.log(answers));
  }, [count]);

  const handleOnClick = () => {
    const data = {
      parentId: id,
      postTitle: 'Answer1',
      postContent: content,
      memberId: 2,
      postView: 0,
      postVoteCount: 0,
      postAnswerCount: 0,
      postCommentCount: 0,
    };
    axios.post('/answers', data).then(() => setCount((el) => el + 1));
  };
  return (
    <Postmain>
      <Mainheader>
        <h1>{props.userdata.postTitle}</h1>
        <button type="button" onClick={handleOnClickask}>
          Ask Question
        </button>
      </Mainheader>
      <Subheader>
        <span>View {props.userdata.postView}</span>
        <span>Answer {answers.length}</span>
        <span>Comment {props.userdata.postCommentCount}</span>
      </Subheader>
      <Postm>
        <VoteBtn />
        <p>{props.userdata.postContent}</p>
        <SF>
          <a href="/">Share</a>
          <span>Following</span>
          <ED>
            <Btn onClick={handleEdit}>Edit</Btn>
            <Btn type="button" onClick={handleRemove}>
              Delete
            </Btn>
          </ED>
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
        <Editor setContent={setContent} />
        <button onClick={handleOnClick}>Post Your Answer</button>
      </PostA>
    </Postmain>
  );
}

export default Post;
