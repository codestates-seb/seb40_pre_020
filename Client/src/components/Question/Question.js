import styles from './Question.module.css';
import { useNavigate } from 'react-router-dom';

// eslint-disable-next-line react/prop-types
function Question({ userData }) {
  const navigate = useNavigate();
  const handleOnClick = () => navigate(`/posts/${userData.id}`);
  return (
    <div className={styles.container1}>
      <div className={styles.container} aria-hidden="true">
        <div className={styles.stats}>
          <div id={styles.votes}>{userData.postVoteCount} votes</div>
          <div id={styles.answer}>{userData.postAnswerCount} answer</div>
          <div id={styles.views}>{userData.postView} views</div>
        </div>
        <div className={styles.content}>
          <div
            className={styles.title}
            onClick={handleOnClick}
            role="button"
            aria-hidden="true"
          >
            {userData.postTitle}
          </div>
          <p>{userData.postContent}</p>
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
