import styles from './Userprofile.module.css';
import {
  faChessKing,
  faClock,
  faCalendarDays,
} from '@fortawesome/free-regular-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useState } from 'react';

function Userprofile() {
  let [a, seta] = useState(1);
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
              3<span>answers</span>
            </div>
            <div className={styles.stat}>
              4<span>questions</span>
            </div>
          </div>
        </div>
        <div>
          <div className={styles.Userdata}>
            <h2>About</h2>
            <div className={styles.about}></div>
          </div>
          <div className={styles.Userdata}>
            <h2>Posts</h2>
            <div className={styles.post}></div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Userprofile;
