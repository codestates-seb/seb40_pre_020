import Tag from '../Addtag/Addtag';
// import Editor from '../editor/editor';
import styles from './UpdateQuestion.module.css';
import { useEffect, useState, useRef } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import styled from 'styled-components';

const Input = styled.input`
  height: 200px;
`;

function UpdateQuestion() {
  useEffect(() => {
    // eslint-disable-next-line no-undef
    axios.get(process.env.REACT_APP_DB_HOST + `/posts/${id}`).then((res) => {
      setBodyText(res.data.data.postContent);
      setTitleText(res.data.data.postTitle);
    });
  }, []);

  // eslint-disable-next-line no-unused-vars
  const [bodyText, setBodyText] = useState('');
  const [titleText, setTitleText] = useState('');
  const navigate = useNavigate();
  const titleRef = useRef(null);
  const contentRef = useRef(null);
  const { id } = useParams();
  const handleOnClick = () => {
    const data = {
      id: id,
      parentId: 0,
      postTitle: titleRef.current.value,
      postContent: contentRef.current.value,
      memberId: 1,
      postView: 0,
      postVoteCount: 0,
      postAnswerCount: 0,
      postCommentCount: 0,
    };
    console.log(data);
    axios
      // eslint-disable-next-line no-undef
      .patch(process.env.REACT_APP_DB_HOST + `/posts/${id}`, data)
      .then(() => navigate('/'));
  };
  return (
    <section className={styles.container}>
      <div className={styles.content}>
        <div className={styles.contentHeader}>
          <h1>Update question</h1>
        </div>
        <div className={styles.editorWrap}>
          <div className={styles.editorHeader}>
            <h3>Title</h3>
            <p>
              Be specific and imagine you`re asking a question to another person
            </p>
            <input
              ref={titleRef}
              value={titleText}
              onChange={(e) => {
                setTitleText(e.target.value);
              }}
              type="text"
              placeholder="e.g is there an R function someone would need to answer your question"
            />
          </div>
          <div className={styles.editorBody}>
            <h3>Body</h3>
            <p>
              Include all the information someone would need to answer you
              question
            </p>
            <Input
              value={bodyText}
              ref={contentRef}
              onChange={(e) => {
                setBodyText(e.target.value);
              }}
            />
          </div>
        </div>
        <Tag></Tag>
        <button
          className={styles.postBtn}
          type="button"
          onClick={handleOnClick}
        >
          Update your question
        </button>
      </div>
    </section>
  );
}
export default UpdateQuestion;
