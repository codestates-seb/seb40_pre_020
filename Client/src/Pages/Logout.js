import styled from 'styled-components';

const Button = styled.div`
  background-color: #e1ecf4;
  color: #39739d;
  border: 1px solid #39739d;
  border-radius: 4px;
  :hover {
    background-color: #b3d3ea;
  }
`;

function Logout() {
  return <Button>Logout</Button>;
}

export default Logout;
