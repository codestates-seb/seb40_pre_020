import styled from 'styled-components';

function Pagination({ total, page, setPage }) {
  const numPages = Math.ceil(total);

  return (
    <>
      <Nav>
        {/* <Button onClick={() => setPage(page - 1)} disabled={page === 1}>
          &lt;
        </Button> */}
        {Array(numPages)
          .fill()
          .map((_, i) => (
            <Button
              key={i + 1}
              onClick={() => setPage(i + 1)}
              aria-current={page === i + 1 ? 'page' : null}
            >
              {i + 1}
            </Button>
          ))}
        {/* <Button onClick={() => setPage(page + 1)} disabled={page === numPages}>
          &gt;
        </Button> */}
      </Nav>
    </>
  );
}

const Nav = styled.div`
  display: flex;
  /* justify-content: center; */
  align-items: center;
  gap: 4px;
  margin: 16px;
`;

const Button = styled.button`
  padding: 4px 8px;
  font-size: 13px;
  font-weight: inherit;
  outline: none;
  border: 1px solid hsl(210, 8%, 85%);
  background-color: transparent;
  border-radius: 3px;

  &:hover {
    background-color: hsl(210, 8%, 85%);
    cursor: pointer;
    /* transform: translateY(-2px); */
  }

  /* &[disabled] {
    background: grey;
    cursor: revert;
    transform: revert;
  } */

  &[aria-current] {
    border-color: transparent !important;
    background-color: hsl(27, 90%, 55%) !important;
    color: #ffffff;
    cursor: default;
  }
`;

export default Pagination;
