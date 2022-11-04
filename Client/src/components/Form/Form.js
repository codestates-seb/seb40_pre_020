import Recap from './Recap';
import SvgIcon from '../SvgIcon/SvgIcon';
import styles from './Form.module.css';
// form 모듈 완성. 소셜버튼 모듈을 보면서 만들어볼것.
//opt-in에 플렉스적용하고, 3개를 가로배열. form 전체를 묶는 흰색 박스 필요.

function Form() {
  const handleSubmit = (event) => {
    event.preventDefault();
    //form 태그 내부 정보 useFetch로 전송
  };
  return (
    <>
      <form onSubmit={handleSubmit} id="login-form" method="POST">
        <div className={styles.displayContainer}>
          <label htmlFor="display-name">Display name</label>
          <input
            className="displany-name"
            id="display-name"
            name="display-name"
            type="text"
          ></input>
        </div>
        <div className="eamil-container">
          <label htmlFor="email-name">Email</label>
          <input
            className="email-name"
            id="email-name"
            name="email-name"
            type="email"
          ></input>
        </div>
        <div className="password-container">
          <label htmlFor="password">Password</label>
          <input
            className="flex--item s-input"
            type="password"
            autoComplete="off"
            name="password"
            id="password"
          ></input>
          <p>
            Passwords must contain at least eight characters, including at least
            1 letter and 1 number.
          </p>
        </div>
        <Recap />
        <div className="opt-in">
          <div className="opt-checkBox">
            <input type="checkBox" id="opt-in" />
          </div>
          <div className="opt-text">
            <label htmlFor="opt-in">
              {' '}
              Opt-in to receive occasional product updates, user research
              invitations, company announcements, and digests.
            </label>
          </div>
          <div className="opt-help">
            <SvgIcon name="questionCircleFill" />
          </div>
        </div>
        <div className="SignUpBtn">
          <input type="submit" value="Sign Up"></input>
        </div>
      </form>
      <div className="js-terms fs-caption fc-light ta-left mt32">
        By clicking “Sign up”, you agree to our{' '}
        <a
          href="https://stackoverflow.com/legal/terms-of-service/public"
          name="tos"
          target="blank"
          className="link"
        >
          terms of service
        </a>
        ,{' '}
        <a
          href="https://stackoverflow.com/legal/privacy-policy"
          name="privacy"
          target="blank"
          className="link"
        >
          privacy policy
        </a>{' '}
        and{' '}
        <a
          href="https://stackoverflow.com/legal/cookie-policy"
          name="cookie"
          target="blank"
          className="link"
        >
          cookie policy
        </a>
      </div>
    </>
  );
}

export default Form;
