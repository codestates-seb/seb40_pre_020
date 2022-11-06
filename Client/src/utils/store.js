const storge = {
  getData: (key) => {
    return JSON.parse(localStorage.getItem(key));
  },
  setData: (key, value) => {
    localStorage.setItem(key, JSON.stringify(value));
  },
};

export { storge };

function getItem(key) {
  return localStorage.getItem(key);
}
export default getItem;
