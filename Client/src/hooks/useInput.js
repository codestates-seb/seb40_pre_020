import { useState, useCallback } from 'react';

function useInput(initialInput) {
  const [input, setInput] = useState(initialInput);
  const onChange = useCallback((event) => {
    const { name, value } = event.target;
    setInput((input) => ({ ...input, [name]: value }));
  }, []);
  const reset = useCallback(() => setInput(initialInput), [initialInput]);
  return [input, onChange, reset];
}

export { useInput };
