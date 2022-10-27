import styles from './Question.module.css';
import { useNavigate } from 'react-router-dom';

// eslint-disable-next-line react/prop-types
function Question({ userData }) {
  const navigate = useNavigate();
  const handleOnClick = () => navigate(`/question/${userData.userId}`);
  return (
    <div className={styles.container1}>
      <div className={styles.container} aria-hidden="true">
        <div className={styles.stats}>
          <div id={styles.votes}>{userData.votes} votes</div>
          <div id={styles.answer}>{userData.replies} answer</div>
          <div id={styles.views}>{userData.views} views</div>
        </div>
        <div className={styles.content}>
          <div
            className={styles.title}
            onClick={handleOnClick}
            role="button"
            aria-hidden="true"
          >
            {userData.title}
          </div>
          <p>{userData.content}</p>
        </div>
      </div>
      {userData.tags && (
        <div className={styles.tags}>
          {userData.tags.map((el, i) => {
            return (
              <div key={i} className={styles.tag}>
                {el}
              </div>
            );
          })}
        </div>
      )}

      <div className={styles.user}>
        <img
          src="https://www.gravatar.com/avatar/e3095d7dc611cccee149cd72e48ce0bd?s=32&d=identicon&r=PG&f=1"
          alt="avatar"
        />
        <span className={styles.userId}>유저이름</span>
        <span className={styles.timeAgo}>{userData.createdAt}</span>
      </div>
    </div>
  );
}
export default Question;