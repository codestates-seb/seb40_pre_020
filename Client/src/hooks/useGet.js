import { useCallback } from 'react';

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

export default useGet;
