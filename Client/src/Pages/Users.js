import styled from 'styled-components';
import User from '../components/Users/User';
import { useState, useEffect } from 'react';
import Leftmenu from '../components/Leftside/Leftside';
import Header from './header.js';
import Footer from './footer.js';

const Homemain = styled.div`
  display: flex;
  justify-content: center;
`;

const H1 = styled.h1`
  padding-top: 100px;
  margin-right: auto;
  font-size: 30px;
  font-weight: 700;
  margin-bottom: 30px;
  margin-left: 70px;
`;

const Input = styled.input`
  width: 300px;
  margin-top: 30px;
  margin-left: 70px;
`;

const Maintags = styled.div`
  max-width: 727px;
  border: 1px solid hsl(210, 8%, 85%);
  border-left-width: 1px;
  border-top-width: 0;
  border-bottom-width: 0;
  border-right-width: 0;
`;

const Gridtags = styled.div`
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  margin-left: 60px;
  margin-top: 10px;
`;

function Users() {
  const [loading, setLoading] = useState(true);
  const [users, setUsers] = useState([]);

  const getUsers = async () => {
    const json = await (
      await fetch(
        'https://api.stackexchange.com/2.3/users?order=desc&sort=reputation&site=stackoverflow'
      )
    ).json();
    setUsers(json.items);
    setLoading(false);
  };

  useEffect(() => {
    getUsers();
  }, []);

  return (
    <div>
      <Header />
      <Homemain>
        <Leftmenu />
        <Maintags>
          <H1>Users</H1>
          <Input placeholder="Filter by user"></Input>
          <div>
            {loading ? (
              <h1>loading...</h1>
            ) : (
              <Gridtags>
                {users.map((Users) => (
                  <User
                    key={Users.user_id}
                    id={Users.user_id}
                    name={Users.display_name}
                    location={Users.location}
                    userimg={Users.profile_image}
                  />
                ))}
              </Gridtags>
            )}
          </div>
        </Maintags>
      </Homemain>
      <Footer />
    </div>
  );
}

export default Users;
