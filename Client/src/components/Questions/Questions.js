import { useState } from 'react';
import styles from './Questions.module.css';
import Question from '../Question/Question';
import Pagination from '../Pagination/Pagination';
import { useNavigate } from 'react-router-dom';
// import RelatedTags from '../RelatedTags/RelatedTags';
function Questions() {
  const navigate = useNavigate();
  const handleOnClick = () => navigate(`/questions/ask`);
  let defaultData = [
    {
      title:
        'How to use ControllerContext of a controller from another controller in ASP.NET MVC and C#?',
      status: '-ㅇㅇㅇㅇㅇㅇㅇ',
      views: 5,
      content: ` know this has been asked before, and I have read a lot of posts about it, but I can't seem to find anything to help. I have about 15 controllersamong many other'`,
      userId: 123123,
      createdAt: '2022 - 10 - 18',
      votes: 5,
      replies: 5,
      totalPages: 10,
      tags: [
        'node.js',
        'java',
        'aws-lamba',
        'aws-organizations',
        'aws-control-tower',
        'node.js',
        'java',
      ],
    },
    {
      title:
        'How to use ControllerContext of a controller from another controller in ASP.NET MVC and C#?',
      status: '-ㅇㅇㅇㅇㅇㅇㅇ',
      views: 5,
      content: ` know this has been asked before, and I have read a lot of posts about it, but I can't seem to find anything to help. I have about 15 controllersamong many other'`,
      userId: 123123,
      createdAt: '2022 - 10 - 18',
      votes: 5,
      replies: 5,
      totalPages: 10,
      tags: [
        'node.js',
        'java',
        'aws-lamba',
        'aws-organizations',
        'aws-control-tower',
      ],
    },
    {
      title:
        'How to use ControllerContext of a controller from another controller in ASP.NET MVC and C#?',
      status: '-ㅇㅇㅇㅇㅇㅇㅇ',
      views: 5,
      content: ` know this has been asked before, and I have read a lot of posts about it, but I can't seem to find anything to help. I have about 15 controllersamong many other'`,
      userId: 123123,
      createdAt: '2022 - 10 - 18',
      votes: 5,
      replies: 5,
      totalPages: 10,
      tags: [
        'node.js',
        'java',
        'aws-lamba',
        'aws-organizations',
        'aws-control-tower',
      ],
    },
    {
      title:
        'How to use ControllerContext of a controller from another controller in ASP.NET MVC and C#?',
      status: '-ㅇㅇㅇㅇㅇㅇㅇ',
      views: 5,
      content: ` know this has been asked before, and I have read a lot of posts about it, but I can't seem to find anything to help. I have about 15 controllersamong many other'`,
      userId: 123123,
      createdAt: '2022 - 10 - 18',
      votes: 5,
      replies: 5,
      totalPages: 10,
      tags: [
        'node.js',
        'java',
        'aws-lamba',
        'aws-organizations',
        'aws-control-tower',
      ],
    },
    {
      title:
        'How to use ControllerContext of a controller from another controller in ASP.NET MVC and C#?',
      status: '-ㅇㅇㅇㅇㅇㅇㅇ',
      views: 5,
      content: ` know this has been asked before, and I have read a lot of posts about it, but I can't seem to find anything to help. I have about 15 controllersamong many other'`,
      userId: 123123,
      createdAt: '2022 - 10 - 18',
      votes: 5,
      replies: 5,
      totalPages: 10,
      tags: [
        'node.js',
        'java',
        'aws-lamba',
        'aws-organizations',
        'aws-control-tower',
      ],
    },
  ];
  const [currentPage, setCurrentPage] = useState(1);
  return (
    <section className={styles.content}>
      <div className={styles.titleWrap}>
        <div className={styles.mainber}>
          <h1>All Questions</h1>
          <button type="button" onClick={handleOnClick}>
            Ask Question
          </button>
        </div>
        <div className={styles.subber}>
          <span>286 questions</span>
          <button type="button">Filter</button>
        </div>
      </div>
      <div className={styles.questions}>
        {defaultData.map((item, i) => {
          return <Question key={i} userData={item}></Question>;
        })}
      </div>
      {/* <RelatedTags /> */}
      <Pagination
        total={10}
        currentPage={currentPage}
        setCurrentPage={setCurrentPage}
      />
    </section>
  );
}
export default Questions;
