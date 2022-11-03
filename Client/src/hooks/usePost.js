import { useCallback } from 'react';

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

export default usePost;
