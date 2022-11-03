import { useEffect, useState, useCallback } from 'react';
import axios from 'axios';

function useFetch(url = '') {
  const [data, setData] = useState([]);
  const [error, setError] = useState(null);
  const [isLoding, setIsLoding] = useState(true);

  useEffect(() => {
    axios
      .get(url)
      .then((res) => {
        setData(res.data);
        setIsLoding(false);
      })
      .catch((err) => {
        setError(err.message);
        setIsLoding(false);
      });
  }, [url]);

  return { data, setData, isLoding, error };
}
const useGet = (url) => {
  const get = useCallback(async () => {
    const response = await fetch(url);
    const data = await response.json();
    if (!response.ok) {
      throw new Error(data.message);
    } else {
      return data;
    }
  }, [url]);

  return get;
};

const usePost = (url) => {
  const get = useCallback(
    async (body) => {
      const response = await fetch(url, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(body),
      });
      const data = await response.json();
      if (!response.ok) {
        throw new Error(data.message);
      } else {
        return data;
      }
    },
    [url]
  );

  return get;
};

export default { useFetch, useGet, usePost };
