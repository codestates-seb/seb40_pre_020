import Tag from '../Addtag/Addtag';
import Editor from '../editor/editor';
import styles from './UpdateQuestion.module.css';
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';

function UpdateQuestion() {
  const [userdata, setuserData] = useState([]);
  const { id } = useParams();
  useEffect(() => {
    axios.get(`/posts/${id}`).then((res) => {
      setuserData(res.data.data);
    });
  }, []);
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
              value={userdata.postTitle}
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
            <Editor postContent={userdata.postContent} />
          </div>
        </div>
        <Tag></Tag>
        <button className={styles.postBtn} type="button">
          Update your question
        </button>
      </div>
    </section>
  );
}
export default UpdateQuestion;
