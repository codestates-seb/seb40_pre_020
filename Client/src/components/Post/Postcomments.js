import styled from 'styled-components';
import axios from 'axios';

const Postscommentmain = styled.div`
  border-radius: 30px;
  padding: 10px;
  display: flex;
  span {
    margin-bottom: 10px;
  }
  button {
    margin-left: auto;
  }
`;

const Postscomment = (item) => {
  const handleDeleteComment = () => {
    axios
      // eslint-disable-next-line no-undef
      .delete(`http://3.39.219.172:8080/comments/${item.item.id}`)
      .then(() => {
        window.location.reload();
      });
  };
  return (
    <Postscommentmain>
      <span>{item.item.commentContent}</span>
      <button onClick={handleDeleteComment}>Delete</button>
    </Postscommentmain>
  );
};

export default Postscomment;
