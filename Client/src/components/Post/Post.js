import VoteBtn from '../VoteBtn/VoteBtn';
import styled from 'styled-components';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import { useEffect, useState } from 'react';
import Editor from '../Editor/editor';
import PostAnswers from './PostAnswers';
import Postscomment from './Postcomments';
import Tag from '../Addtag/Addtag';
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
  padding: 10px;
  h1 {
    padding: 10px;
  }
  button {
    margin-top: 30px;
    padding: 10px;
  }
`;

const PostC = styled.div`
  padding: 10px;
  border-top: 1px solid rgba(0, 0, 0, 0.1);
  h1 {
    margin-bottom: 15px;
  }
`;

const Postcomment = styled.div`
  display: flex;
  margin-top: 15px;
  padding: 10px;
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
`;

const H1 = styled.div`
  padding: 10px;
`;

const ED = styled.div`
  margin-left: auto;
`;

function Post(props) {
  const navigate = useNavigate();
  const [answers, setAnswers] = useState([]);
  const [content, setContent] = useState();
  // eslint-disable-next-line no-unused-vars
  const [tags, setTags] = useState([]);
  const [count, setCount] = useState(0);
  const handleOnClickask = () => navigate(`/questions/ask`);
  const { id } = useParams();
  const [comment, setComment] = useState([]);
  const [commentvalue, setCommentValue] = useState('');

  const handleRemove = () => {
    axios
      // eslint-disable-next-line no-undef
      .delete(`http://3.39.219.172:8080/posts/${id}`)
      .then(() => navigate(`/`));
  };

  const handleEdit = () => {
    navigate(`/questions/update/${id}`);
  };
  const handleOnClick = () => {
    const data = {
      parentId: id,
      postTitle: 'Answer1',
      postContent: content,
      memberId: 1,
      postView: 0,
      postVoteCount: 0,
      postAnswerCount: 0,
      postCommentCount: 0,
    };
    axios
      // eslint-disable-next-line no-undef
      .post('http://3.39.219.172:8080/answers', data)
      .then(() => setCount((el) => el + 1))
      .then((res) => console.log(res.data))
      .catch((Error) => console.log(Error));
  };

  useEffect(() => {
    axios
      // eslint-disable-next-line no-undef
      .get(`http://3.39.219.172:8080/answers/${id}?page=1&size=20`)
      .then((res) => setAnswers(res.data.data));
  }, [count]);

  useEffect(() => {
    const data = {
      postEntities: [
        {
          postId: id,
          tagName: tags[0],
        },
      ],
    };
    // eslint-disable-next-line no-undef
    axios.post(`http://3.39.219.172:8080/tags`, data);
  }, [tags]);

  const handlecomment = () => {
    const data = {
      memberId: 1,
      postId: id,
      commentContent: commentvalue,
    };
    axios
      // eslint-disable-next-line no-undef
      .post('http://3.39.219.172:8080/comments', data)
      .then(() => setCount((el) => el + 1))
      .then(() => setCommentValue(''));
  };

  useEffect(() => {
    axios
      // eslint-disable-next-line no-undef
      .get(`http://3.39.219.172:8080/comments/${id}?page=1&size=20`)
      .then((res) => setComment(res.data.data));
  }, [count]);

  const onCommentChange = (e) => {
    setCommentValue(e.target.value);
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
        <Tag setTags={setTags} />
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
      <PostC>
        <h1>{comment.length} Comments</h1>
        {comment.map((item, i) => {
          return <Postscomment item={item} key={i}></Postscomment>;
        })}
        <Postcomment>
          <form>
            <input
              type="text"
              placeholder="Add a comment"
              onChange={onCommentChange}
            />
            <button onClick={handlecomment}>Answer Your Comment</button>
          </form>
        </Postcomment>
      </PostC>
      <H1>{answers.length} Answers</H1>
      {answers.map((item, i) => {
        return <PostAnswers item={item} key={i}></PostAnswers>;
      })}
      <PostA>
        <h1>Your Answer</h1>
        <form>
          <Editor setContent={setContent} />
          <button onClick={handleOnClick}>Post Your Answer</button>
        </form>
      </PostA>
    </Postmain>
  );
}

export default Post;
