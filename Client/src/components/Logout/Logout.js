import styled from 'styled-components';
import { clearToken } from '../../utils/token';
const LogoutBtn = styled.button`
  width: 100%;
  margin-left: 10%;
  background-color: #e1ecf4;
  color: #39739d;
  border: 1px solid #39739d;
  border-radius: 4px;
  :hover {
    background-color: #b3d3ea;
  }
`;
const loggedOut = () => {
  clearToken();
};
function Logout({ changeLoginStatus }) {
  return (
    <>
      <LogoutBtn
        onClick={() => {
          loggedOut();
          changeLoginStatus(false);
          window.location.reload();
        }}
      >
        Log Out
      </LogoutBtn>
    </>
  );
}
export default Logout;
