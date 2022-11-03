import axios from 'axios';
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import Header from './header';
import styled from 'styled-components';
import Leftmenu from '../components/Leftside/Leftside';
import Rightmenu from '../components/Rightside/Rightside';
import Footer from './footer';
import Post from '../components/Post/Post';

const Postsmain = styled.div`
  display: flex;
  justify-content: center;
`;

const Posts2 = styled.div`
  padding-top: 100px;
  width: 1000px;
`;

function Posts() {
  const { id } = useParams();
  // eslint-disable-next-line no-unused-vars
  const [userdata, setuserData] = useState([]);

  useEffect(() => {
    axios.get(`/posts/${id}`).then((res) => {
      setuserData(res.data.data);
    });
  }, []);

  console.log(userdata);
  return (
    <div>
      <Header />
      <Postsmain>
        <Leftmenu />
        <Posts2>
          <Post userdata={userdata} />
        </Posts2>
        <Rightmenu />
      </Postsmain>
      <Footer />
    </div>
  );
}

export default Posts;
