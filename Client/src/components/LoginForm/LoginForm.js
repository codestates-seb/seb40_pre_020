import SvgIcon from '../SvgIcon/SvgIcon';
import styles from './LoginForm.module.css';
import axios from 'axios';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
function LoginForm() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();
  //Login 요청
  const loginReq = () => {
    const data = {
      email: email,
      password: password,
    };
    axios
      .post('v1/auth/login', data)
      .then((res) => {
        //handleSuccess
        alert('Well done!');
        let jwtToken = res.headers.get('Authorization');
        localStorage.setItem('Authorization', jwtToken);
        //return res.json();
        navigate('/');
      })
      //   .then((res))=>{
      // if(res.code ===1){
      //dispathcher(memberLogin(res.data))
      //setMember(res.data);
      //alert(res.data.name + "님 로그인 성공");
      // }
      //   }
      .catch((error) => {
        alert('an error', error.response);
      });
  };
  return (
    <div>
      <div className={styles.FormContainer}>
        <div>
          <div>Email</div>
          <div>
            <input
              onChange={(event) => {
                setEmail(event.target.value);
              }}
              placeholder="이메일을 입력하십시오"
            ></input>
          </div>
        </div>
        <div>
          <div>Password</div>
          <div>
            <input
              onChange={(event) => {
                setPassword(event.target.value);
              }}
              placeholder="비밀번호를 입력하십시오. (영문 숫자 포함 8자리 이상)"
            ></input>
          </div>
        </div>
        <div className={styles.pContainer}>
          <p>
            Passwords must contain at least eight characters, including at least
            1 letter and 1 number.
          </p>
        </div>
        <div className={styles.loginBtnContainer}>
          <button
            className={styles.loginBtn}
            onClick={() => {
              loginReq();
            }}
          >
            Login
          </button>
        </div>
      </div>
      <div className={styles.textWrap}>
        <div>
          Don’t have an account? <em>Sign up</em>
        </div>
        <div>
          Are you an employer?
          <em>Sign up on Talent</em>
          <SvgIcon name="share" className={styles.test} />
        </div>
      </div>
    </div>
  );
}
export default LoginForm;
