import styled from 'styled-components';

function User({ location, name, userimg }) {
  const Usertab = styled.div`
    border: 1px solid rgba(0, 0, 0, 0.3);
    border-radius: 5px;
    width: 200px;
    height: 50px;
    display: flex;
    padding: 10px;
    margin: 10px;
    P {
      color: #777;
      font-size: 12px;
    }
  `;

  const Img = styled.img`
    border-radius: 50%;
    width: 30px;
    height: 30px;
  `;

  const H2 = styled.h2`
    font-size: 15px;
    color: blue;
  `;

  const Userinfo = styled.div`
    margin-left: 10px;
    margin-top: 3px;
    p {
      margin-top: 3px;
    }
  `;
  return (
    <Usertab>
      <Img src={userimg} alt="img"></Img>
      <Userinfo>
        <H2>{name}</H2>
        <p>{location}</p>
      </Userinfo>
    </Usertab>
  );
}

export default User;
