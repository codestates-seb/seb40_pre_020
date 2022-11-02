import VoteBtn from '../VoteBtn/VoteBtn';
import styled from 'styled-components';

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
  a {
    font-size: 15px;
    text-decoration: none;
    color: inherit;
    margin-right: 15px;
  }
`;

function Post(props) {
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
    </Postmain>
  );
}

export default Post;
