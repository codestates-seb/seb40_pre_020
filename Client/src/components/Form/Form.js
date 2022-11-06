//import Recap from './Recap';
import SvgIcon from '../SvgIcon/SvgIcon';
import styles from './Form.module.css';
import axios from 'axios';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
//import usePost from '../../hooks/usePost';
//import { useDispatch } from 'react-redux';
// form 모듈 완성. 소셜버튼 모듈을 보면서 만들어볼것.
//opt-in에 플렉스적용하고, 3개를 가로배열. form 전체를 묶는 흰색 박스 필요.

function Form() {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();
  //회원가입 요청
  const register = () => {
    const data = {
      name: name,
      email: email,
      password: password,
    };
    axios
      // eslint-disable-next-line no-undef
      .post(process.env.REACT_APP_DB_HOST + 'v1/signup', data)
      .then((response) => {
        //handleSuccess
        alert('Well done!');
        alert(response.headers);
        console.log('User token', response.headers.data.jwt);
        localStorage.setItem('token', response.headers.data.jwt);
        navigate('/');
      })
      .catch((error) => {
        alert('an error', error.response);
      });
  };
  // const handleSubmit = async (event) => {
  //   const response = await fetch('로그인 API 주소', {
  //     method: 'POST',
  //     headers: { 'Content-Type': 'application/json' },
  //     body: JSON.stringify({
  //       name: event.target.elements.displayName.value,
  //       email: event.target.elements.emailName.value,
  //       password: event.target.elements.password.value,
  //     }),
  //   });
  //   const json = await response.json();
  //   if (!response.ok) {
  //     alert(json.message);
  //   } else {
  //     alert('success');
  //     navigate('/');
  //   }
  // };
  // const postSignUp = usePost('http://3.39.219.172:8080/v1/signup');
  // const navigate = useNavigate();
  // const handleSubmit = (event) => {
  //   event.preventDefault();
  //   return postSignUp({
  //     name: event.target.elements.displayName.value,
  //     email: event.target.elements.emailName.value,
  //     password: event.target.elements.password.value,
  //   });
  //navigate('/');

  // handleFormChange감지 name,password
  // const handleButtonChange = () => {
  //   const isNumberPassword = /[0-9]/g;
  //   const isAlphaPassword = /[a-z]/gi; //10~20 영어, 숫자만 허용
  //   id.length >= 6 &&
  //   password.length >= 10 &&
  //   isNumberPassword.test(password) &&
  //   isAlphaPassword.test(password)
  //     ? setPasswordAlert(false)
  //     : setPasswordAlert(true);
  // };

  //form 태그 내부 정보 useFetch로 전송
  //name: ${event.target.elements.displayName.value}, email: ${event.target.elements.emailName.value} , password: ${event.target.elements.password}
  //          {/*onSubmit={() => {
  //     register();
  //   }}
  //   id="login-form"
  // method="POST"*/}
  return (
    <>
      <div className={styles.formContainer}>
        <div className={styles.displayContainer}>
          <div className={styles.labelMainCotainer}>
            <label className={styles.labelMain} htmlFor="displayName">
              Display name
            </label>
          </div>
          <div className={styles.inputMainContainer}>
            <input
              value={name}
              onChange={(event) => {
                setName(event.target.value);
              }}
              className="displanyName"
              id="displayName"
              name="displayName"
              type="text"
              placeholder="아이디를 입력하십시오. (6자리 이상)"
            ></input>
          </div>
        </div>
        <div className={styles.emailContainer}>
          <div className={styles.labelMainCotainer}>
            <label className={styles.labelMain} htmlFor="emailName">
              Email
            </label>
          </div>
          <div className={styles.inputMainContainer}>
            <input
              value={email}
              onChange={(event) => {
                setEmail(event.target.value);
              }}
              className="emailName"
              id="emailName"
              name="emailName"
              type="email"
              placeholder="이메일을 입력하십시오."
            ></input>
          </div>
        </div>
        <div className={styles.passwordContainer}>
          <div className={styles.labelMainCotainer}>
            <label className={styles.labelMain} htmlFor="password">
              Password
            </label>
          </div>
          <div className={styles.inputMainContainer}>
            <input
              value={password}
              onChange={(event) => {
                setPassword(event.target.value);
              }}
              className="flex--item s-input"
              type="password"
              autoComplete="off"
              name="password"
              id="password"
              placeholder="비밀번호를 입력하십시오. (영문 숫자 포함 8자리 이상)"
            ></input>
          </div>
          <div>
            <p className={styles.pwHelpMsg}>
              Passwords must contain at least eight characters, including at
              least 1 letter and 1 number.
            </p>
          </div>
        </div>
        <div className={styles.RecapComponent}></div>
        <div className={styles.optIn}>
          <div className={styles.optCheckBox}>
            <input type="checkBox" id="opt-in" />
          </div>
          <div className={styles.optText}>
            <label className={styles.labelOpt} htmlFor="opt-in">
              {' '}
              Opt-in to receive occasional product updates, user research
              invitations, company announcements, and digests.
            </label>
          </div>
          <div className={styles.optHelp}>
            <SvgIcon name="questionCircleFill" />
          </div>
        </div>
        <input
          className={styles.signUpBtn}
          type="submit"
          value="Sign Up"
          onClick={() => {
            register();
          }}
        ></input>
        <div className={styles.policyContainer}>
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
      </div>
    </>
  );
}

export default Form;
