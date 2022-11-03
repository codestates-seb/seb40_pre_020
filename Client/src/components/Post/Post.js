import VoteBtn from '../VoteBtn/VoteBtn';
import styled from 'styled-components';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import { useEffect, useState } from 'react';

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
  display: flex;
  padding: 15px;
  margin-top: 30px;
  p {
    color: #777;
    padding: 15px;
  }
`;

const SF = styled.div`
  display: flex;
  margin-top: 100px;
  margin-left: -70px;
  text-align: center;
  align-items: center;
  a {
    font-size: 15px;
    text-decoration: none;
    color: inherit;
    margin-right: 15px;
  }
`;
const DeleteBtn = styled.button`
  color: black;
  background-color: white;
  font-size: 15px;

  color: inherit;
  margin-right: 15px;
`;

function Post(props) {
  const navigate = useNavigate();
  const [data, setData] = useState([]);
  const handleOnClick = () => navigate(`/questions/ask`);
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
      .then((res) => setData(res.data.data));
  }, []);
  console.log(data);
  return (
    <Postmain>
      <Mainheader>
        <h1>{props.userdata.postTitle}</h1>
        <button type="button" onClick={handleOnClick}>
          Ask Question
        </button>
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
          <button onClick={handleEdit}>Edit</button>
          <DeleteBtn type="button" onClick={handleRemove}>
            Delete
          </DeleteBtn>
        </SF>
      </Postm>
      {data.map((el, i) => {
        return <div key={i}>{el.postContent}</div>;
      })}
    </Postmain>
  );
}

export default Post;
