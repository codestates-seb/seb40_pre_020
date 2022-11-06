import Editor from '../Editor/Editor';
import styles from './AskQuestion.module.css';
import { useRef, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function AskQuestion() {
  // eslint-disable-next-line no-unused-vars
  let [content, setContent] = useState('');
  const titleRef = useRef(null);
  const navigate = useNavigate();

  const handleOnClick = () => {
    const data = {
      parentId: 0,
      postTitle: titleRef.current.value,
      postContent: content,
      memberId: 1,
      postView: 0,
      postVoteCount: 0,
      postAnswerCount: 0,
      postCommentCount: 0,
    };
    console.log(data);
    axios
      // eslint-disable-next-line no-undef
      .post(process.env.REACT_APP_DB_HOST + '/posts', data)
      .then(() => navigate('/'));
  };

  return (
    <section className={styles.container}>
      <div className={styles.content}>
        <div className={styles.contentHeader}>
          <h1>Ask a public question</h1>
        </div>
        <div className={styles.editorWrap}>
          <div className={styles.editorHeader}>
            <h3>Title</h3>
            <p>
              Be specific and imagine you`re asking a question to another person
            </p>
            <input
              ref={titleRef}
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
            <Editor setContent={setContent} />
          </div>
        </div>

        <button
          className={styles.postBtn}
          type="button"
          onClick={handleOnClick}
        >
          Review your question
        </button>
      </div>
    </section>
  );
}
export default AskQuestion;
