import { useState, useEffect } from 'react';
import styles from './Questions.module.css';
import Question from '../Question/Question';
import Pagination from '../Pagination/Pagination';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

// import RelatedTags from '../RelatedTags/RelatedTags';
function Questions() {
  const [limit] = useState(10); //한 페이지 최대 개시물 수
  const [page, setPage] = useState(1);
  // eslint-disable-next-line no-unused-vars
  const [defaultData, setDd] = useState([]);
  // const offset = (page - 1) * limit;
  const navigate = useNavigate();
  const handleOnClick = () => navigate(`/questions/ask`);
  const ad = () => {
    axios
      .get(`/posts?page=${page}&size=${limit}`)
      .then((data) => {
        setDd(data.data.data);
      })
      .catch((er) => {
        console.log(er);
      });
  };
  useEffect(() => {
    ad();
  }, [page]);
  console.log(defaultData);
  // const [currentPage, setCurrentPage] = useState(1);
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
          <span>{defaultData.length} questions</span>
          <button type="button">Filter</button>
        </div>
      </div>
      <div className={styles.questions}>
        {defaultData.map((item, i) => {
          return <Question key={i} userData={item}></Question>;
        })}
      </div>
      <Pagination
        total={defaultData.length} //게시물 갯수
        limit={limit}
        page={page}
        setPage={setPage}
      />
    </section>
  );
}
export default Questions;
