import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import './Editor.css';
import React from 'react';

const Editor = ({ setContent }) => {
  return (
    <div className="App">
      <CKEditor
        editor={ClassicEditor}
        data=""
        onChange={(event, editor) => {
          const data = editor.getData();
          setContent(data.replace(/<[^>]*>?/g, ''));
        }}
        onBlur={(event, editor) => {
          console.log('Blur.', editor);
        }}
        onFocus={(event, editor) => {
          console.log('Focus.', editor);
        }}
      />
    </div>
  );
};

export default React.memo(Editor);
