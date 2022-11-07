import styles from './Question.module.css';
import { useNavigate } from 'react-router-dom';
// eslint-disable-next-line react/prop-types
function Question({ userData }) {
  // eslint-disable-next-line no-unused-vars

  let date = new Date().toLocaleString('ko-kr');
  const navigate = useNavigate();
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
            onClick={() => {
              navigate(`/posts/${userData.id}`);
            }}
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
        <span
          className={styles.userId}
          role="button"
          aria-hidden="true"
          onClick={(event) => {
            event.stopPropagation();
            navigate(`/mypage/${userData.memberId}`);
          }}
        >
          {userData.memberId}
        </span>
        <span className={styles.timeAgo}>{date}</span>
      </div>
    </div>
  );
}
export default Question;
