import { useState, useEffect } from 'react';
import styles from './Questions.module.css';
import Question from '../Question/Question';
import Pagination from '../Pagination/Pagination';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

function Questions() {
  const [limit] = useState(2); //한 페이지 최대 개시물 수
  const [page, setPage] = useState(1);
  const [total, setTotal] = useState([]);
  const [totalEl, setTotalEl] = useState([]);
  const [postsData, setPostsData] = useState([]);
  const navigate = useNavigate();
  const handleOnClick = () => navigate(`/questions/ask`);
  const getPosts = () => {
    axios
      .get(`/posts?page=${page}&size=${limit}`)
      .then((data) => {
        setPostsData(data.data.data);
        setTotal(data.data.pageInfo.totalPages);
        setTotalEl(data.data.pageInfo.totalElements);
      })
      .catch((er) => {
        console.log(er);
      });
  };
  useEffect(() => {
    getPosts();
  }, [page]);
  return (
    <section className={styles.content}>
      <div className={styles.titleWrap}>
        <div className={styles.mainber}>
          <h1>All Questions</h1>
          <button type="button" onClick={handleOnClick}>
            Ask Question
          </button>
        </div>
        <div className={styles.subber}>
          <span>{totalEl} questions</span>
          <button type="button">Filter</button>
        </div>
      </div>
      <div className={styles.questions}>
        {postsData
          .sort((a, b) => b.id - a.id)
          .map((item, i) => {
            return <Question key={i} userData={item}></Question>;
          })}
      </div>
      <Pagination total={total} page={page} setPage={setPage} />
    </section>
  );
}
export default Questions;
