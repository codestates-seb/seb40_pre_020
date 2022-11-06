import styles from './Userprofile.module.css';
import {
  faChessKing,
  faClock,
  faCalendarDays,
} from '@fortawesome/free-regular-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';

function Userprofile() {
  const navigate = useNavigate();
  let [a, seta] = useState(1);
  let { id } = useParams();
  let [userdata, setUserData] = useState([]);
  let [comment, setComment] = useState([]);
  useEffect(() => {
    // eslint-disable-next-line import/no-named-as-default-member
    axios
      .all([
        axios.get(
          // eslint-disable-next-line no-undef
          process.env.REACT_APP_DB_HOST + `/profiles/${id}/posts?page=1&size=20`
        ),
        axios.get(
          // eslint-disable-next-line no-undef
          process.env.REACT_APP_DB_HOST +
            `/profiles/${id}/answers?page=1&size=20`
        ),
      ])
      .then(
        // eslint-disable-next-line import/no-named-as-default-member
        axios.spread((res1, res2) => {
          setUserData(res1.data.data);
          setComment(res2.data.data);
        })
      );
  }, []);
  return (
    <div className={styles.container}>
      <div className={styles.Userprofile}>
        <img
          src="https://lh3.googleusercontent.com/a/ALm5wu13X0XLApYPHbBO2kYOr8i6znu8rpu6NY2Jasi_=k-s256"
          alt="avatar"
        />
        <div className={styles.userdata}>
          <div className={styles.username}>유저이름</div>
          <div className={styles.userinfo}>
            <FontAwesomeIcon
              icon={faChessKing}
              size="1x"
              color="rgb(106, 115, 124)"
            />
            <span> Member for 4 days </span>
            <FontAwesomeIcon
              icon={faClock}
              size="1x"
              color="rgb(106, 115, 124)"
            />
            <span> Last seen this week </span>
            <FontAwesomeIcon
              icon={faCalendarDays}
              size="1x"
              color="rgb(106, 115, 124)"
            />
            <span> Visited 4 days, 4 consecutive </span>
          </div>
        </div>
      </div>
      <div className={styles.navbar}>
        <button className={a === 1 ? styles.on : null} onClick={() => seta(1)}>
          Profile
        </button>
        <button className={a === 2 ? styles.on : null} onClick={() => seta(2)}>
          Activity
        </button>
        <button className={a === 3 ? styles.on : null} onClick={() => seta(3)}>
          Saves
        </button>
        <button className={a === 4 ? styles.on : null} onClick={() => seta(4)}>
          Settings
        </button>
      </div>
      <div className={styles.sss}>
        <div className={styles.stats}>
          <h2>Stats</h2>
          <div className={styles.statsinfo}>
            <div className={styles.stat}>
              1<span>reputation</span>
            </div>
            <div className={styles.stat}>
              2<span>reached</span>
            </div>
            <div className={styles.stat}>
              {comment.length}
              <span>comments</span>
            </div>
            <div className={styles.stat}>
              {userdata.length}
              <span>questions</span>
            </div>
          </div>
        </div>
        <div>
          <div className={styles.Userdata}>
            <h2>Comment</h2>
            <div className={styles.about}>
              {comment.map((el) => {
                return (
                  <div key={el.id} className={styles.container22}>
                    <div className={styles.container33}>
                      <div className={styles.stats33}>
                        <div id={styles.votes}>{el.postVoteCount} votes</div>
                      </div>
                      <div>{el.postContent}</div>
                    </div>
                  </div>
                );
              })}
            </div>
          </div>
          <div className={styles.Userdata}>
            <h2>Posts</h2>
            <div className={styles.posts}>
              {userdata.map((el) => {
                return (
                  <div className={styles.container22} key={el.id}>
                    <div className={styles.container33} aria-hidden="true">
                      <div className={styles.stats33}>
                        <div id={styles.votes}>{el.postVoteCount} votes</div>
                        <div id={styles.answer}>
                          {el.postAnswerCount} answer
                        </div>
                        <div id={styles.views}>{el.postView} views</div>
                      </div>
                      <div className={styles.content}>
                        <div
                          className={styles.title}
                          onClick={() => navigate(`/posts/${el.id}`)}
                          role="button"
                          aria-hidden="true"
                        >
                          {el.postTitle}
                        </div>
                        <p>{el.postContent}</p>
                      </div>
                    </div>
                  </div>
                );
              })}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Userprofile;
