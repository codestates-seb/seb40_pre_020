import styled from 'styled-components';
import Tag from '../components/Tags/tag';
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
  p {
    margin-left: 70px;
  }
`;

const Gridtags = styled.div`
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  margin-left: 60px;
  margin-top: 10px;
`;

function Tags() {
  const [loading, setLoading] = useState(true);
  const [tags, setTags] = useState([]);

  const getTags = async () => {
    const json = await (
      await fetch(
        'https://api.stackexchange.com/2.3/tags?order=desc&sort=popular&site=stackoverflow'
      )
    ).json();
    setTags(json.items);
    setLoading(false);
  };

  useEffect(() => {
    getTags();
  }, []);

  return (
    <div>
      <Header />
      <Homemain>
        <Leftmenu />
        <Maintags>
          <H1>Tags</H1>
          <p>
            A tag is a keyword or label that categorizes your question with
            other, similar questions.
            <br />
            Using the right tags makes it easier for others to find and answer
            your question.
          </p>
          <Input placeholder="Filter by tag name"></Input>
          <div>
            {loading ? (
              <h1>loading...</h1>
            ) : (
              <Gridtags>
                {tags.map((tags) => (
                  <Tag key={tags.name} id={tags.name} count={tags.count} />
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

export default Tags;
