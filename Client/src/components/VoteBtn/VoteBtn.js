import styled from 'styled-components';
import PropTypes from 'prop-types';
import axios from 'axios';
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

const ArrowUp = styled.div`
  width: 0;
  height: 0;
  border-left: ${(props) => (props.size === 'sm' ? '10px' : '20px')} solid
    transparent;
  border-right: ${(props) => (props.size === 'sm' ? '10px' : '20px')} solid
    transparent;
  border-bottom: ${(props) => (props.size === 'sm' ? '10px' : '20px')} solid
    ${(props) => (props.uservote === 1 ? '#f48024' : '#888')};
`;
const ArrowBottom = styled.div`
  width: 0;
  height: 0;
  border-left: ${(props) => (props.size === 'sm' ? '10px' : '20px')} solid
    transparent;
  border-right: ${(props) => (props.size === 'sm' ? '10px' : '20px')} solid
    transparent;
  border-top: ${(props) => (props.size === 'sm' ? '10px' : '20px')} solid
    ${(props) => (props.uservote === -1 ? '#f48024' : '#888')};
  padding: 0;
`;
const ArrowButton = styled.button`
  border: 0;
  background: none;
  font-size: 2rem;
  cursor: pointer;
  text-align: center;
  padding: 0;
`;
const Total = styled.div`
  text-align: center;
  width: ${(props) => (props.size === 'sm' ? '20px' : '40px')};
  padding: ${(props) => (props.size === 'sm' ? '2px 0 3px' : '7px 0 7px')};
  font-size: ${(props) => (props.size === 'sm' ? '.8rem' : '1.4rem')};
  line-height: ${(props) => (props.size === 'sm' ? '.6rem' : '1.4rem')};
  color: #888;
`;
function VoteBtn(props) {
  const [count, setCount] = useState(0);
  const [userdata, setuserData] = useState([]);
  const { id } = useParams();
  // eslint-disable-next-line no-unused-vars
  useEffect(() => {
    axios.get(`/posts/${id}`).then((res) => {
      setuserData(res.data.data);
    });
  }, [count]);

  const handleVoteClick = (as) => {
    const data = {
      postId: props.postId1 || userdata.id,
      memberId: props.memberId1 || userdata.memberId,
      voteType: as,
    };
    axios.post('/votes', data).then(() => setCount((el) => el + 1));
  };
  return (
    <div {...props}>
      <ArrowButton onClick={() => handleVoteClick(1)}>
        <ArrowUp size={props.size} />
      </ArrowButton>
      <Total size={props.size}>
        {props.postVoteCount1 ? props.postVoteCount1 : userdata.postVoteCount}
      </Total>
      <ArrowButton onClick={() => handleVoteClick(-1)}>
        <ArrowBottom size={props.size} />
      </ArrowButton>
    </div>
  );
}

VoteBtn.propTypes = {
  initialTotal: PropTypes.number.isRequired,
  initialUserVote: PropTypes.number,
  postId: PropTypes.number.isRequired,
};

export default VoteBtn;
