import styled from 'styled-components';

function Tag({ id, count }) {
  const Tagtab = styled.div`
    border: 1px solid rgba(0, 0, 0, 0.3);
    border-radius: 5px;
    width: 150px;
    height: 100px;
    display: flex;
    flex-direction: column;
    padding: 10px;
    margin: 10px;
    p {
      color: #777;
      margin-top: 50px;
    }
  `;

  const H2 = styled.h2`
    color: #999;
    font-size: 15px;
    border: 1px solid none;
    border-radius: 10px;
    background-color: #b4cbd9;
    text-align: center;
    width: 70px;
  `;

  return (
    <Tagtab>
      <H2>{id}</H2>
      <p>{count} questions</p>
    </Tagtab>
  );
}

export default Tag;
