import styles from './RelatedTags.module.css';
function RelatedTags() {
  let tags = [
    'node.js',
    'java',
    'aws-lamba',
    'aws-organizations',
    'aws-control-tower',
    'node.js',
    'java',
    'aws-lamba',
    'aws-organizations',
    'aws-control-tower',
  ];
  return (
    <div className={styles.container}>
      <h2 className={styles.title}>Related Tags</h2>
      {tags.map((el, i) => {
        return (
          <div className={styles.tagBox} key={i}>
            <span className={styles.tag}>{el}</span>
            <span className={styles.deleteBtn}>×</span>
          </div>
        );
      })}
    </div>
  );
}

export default RelatedTags;
