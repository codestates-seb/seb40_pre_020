import styled from 'styled-components';
import RelatedTags from '../RelatedTags/RelatedTags';

const Rightside = styled.div`
  margin-top: 200px;
  padding: 30px;
`;

function Rightmenu() {
  return (
    <Rightside>
      <RelatedTags />
    </Rightside>
  );
}

export default Rightmenu;
