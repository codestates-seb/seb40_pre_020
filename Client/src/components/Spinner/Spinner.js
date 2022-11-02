import styles from './Spinner.module.css';

function Spinner() {
  let spinnerSrc = `https://raw.githubusercontent.com/codestates-seb/seb39_pre_038/main/frontend/src/img/spinner.gif`;
  return (
    <div className={styles.container}>
      <img src={spinnerSrc} alt="spinner" />
    </div>
  );
}

export default Spinner;
