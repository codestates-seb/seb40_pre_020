//import React from 'react';

function SvgIcon({ name = '', width = 32, height = 37 }) {
  const IconType = {
    gitHub: (
      <svg width={18} height={18} viewBox="0 0 18 18">
        <path
          fill="#fdfdfd"
          d="M9 1a8 8 0 0 0-2.53 15.59c.4.07.55-.17.55-.38l-.01-1.49c-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82a7.42 7.42 0 0 1 4 0c1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48l-.01 2.2c0 .21.15.46.55.38A8.01 8.01 0 0 0 9 1Z"
        />
      </svg>
    ),
    google: (
      <svg width={18} height={18} viewBox="0 0 18 18">
        <path
          fill="#4285F4"
          d="M16.51 8H8.98v3h4.3c-.18 1-.74 1.48-1.6 2.04v2.01h2.6a7.8 7.8 0 0 0 2.38-5.88c0-.57-.05-.66-.15-1.18Z"
        />
        <path
          fill="#34A853"
          d="M8.98 17c2.16 0 3.97-.72 5.3-1.94l-2.6-2a4.8 4.8 0 0 1-7.18-2.54H1.83v2.07A8 8 0 0 0 8.98 17Z"
        />
        <path
          fill="#FBBC05"
          d="M4.5 10.52a4.8 4.8 0 0 1 0-3.04V5.41H1.83a8 8 0 0 0 0 7.18l2.67-2.07Z"
        />
        <path
          fill="#EA4335"
          d="M8.98 4.18c1.17 0 2.23.4 3.06 1.2l2.3-2.3A8 8 0 0 0 1.83 5.4L4.5 7.49a4.77 4.77 0 0 1 4.48-3.3Z"
        />
      </svg>
    ),
    faceBook: (
      <svg width={18} height={18} viewBox="0 0 18 18">
        <path
          fill="#fdfdfd"
          d="M3 1a2 2 0 0 0-2 2v12c0 1.1.9 2 2 2h12a2 2 0 0 0 2-2V3a2 2 0 0 0-2-2H3Zm6.55 16v-6.2H7.46V8.4h2.09V6.61c0-2.07 1.26-3.2 3.1-3.2.88 0 1.64.07 1.87.1v2.16h-1.29c-1 0-1.19.48-1.19 1.18V8.4h2.39l-.31 2.42h-2.08V17h-2.5Z"
        />
      </svg>
    ),
    stackOverFlow: (
      <svg width={width} height={height} viewBox="0 0 32 37">
        <path fill="#BCBBBB" d="M26 33v-9h4v13H0V24h4v9h22Z" />
        <path
          fill="#F48024"
          d="m21.5 0-2.7 2 9.9 13.3 2.7-2L21.5 0ZM26 18.4 13.3 7.8l2.1-2.5 12.7 10.6-2.1 2.5ZM9.1 15.2l15 7 1.4-3-15-7-1.4 3Zm14 10.79.68-2.95-16.1-3.35L7 23l16.1 2.99ZM23 30H7v-3h16v3Z"
        />
      </svg>
    ),
    share: (
      <svg width={14} height={14} viewBox="0 0 14 14">
        <path
          fill="#0074cc"
          d="M5 1H3a2 2 0 0 0-2 2v8c0 1.1.9 2 2 2h8a2 2 0 0 0 2-2V9h-2v2H3V3h2V1Zm2 0h6v6h-2V4.5L6.5 9 5 7.5 9.5 3H7V1Z"
        />
      </svg>
    ),
    question: (
      <svg width={26} height={26}>
        <path
          fill="hsl(206,100%,52%)"
          opacity=".5"
          d="M4.2 4H22a2 2 0 012 2v11.8a3 3 0 002-2.8V5a3 3 0 00-3-3H7a3 3 0 00-2.8 2z"
        />
        <path
          fill="hsl(206,100%,52%)"
          d="M1 7c0-1.1.9-2 2-2h18a2 2 0 012 2v12a2 2 0 01-2 2h-2v5l-5-5H3a2 2 0 01-2-2V7zm10.6 11.3c.7 0 1.2-.5 1.2-1.2s-.5-1.2-1.2-1.2c-.6 0-1.2.4-1.2 1.2 0 .7.5 1.1 1.2 1.2zm2.2-5.4l1-.9c.3-.4.4-.9.4-1.4 0-1-.3-1.7-1-2.2-.6-.5-1.4-.7-2.4-.7-.8 0-1.4.2-2 .5-.7.5-1 1.4-1 2.8h1.9v-.1c0-.4 0-.7.2-1 .2-.4.5-.6 1-.6s.8.1 1 .4a1.3 1.3 0 010 1.8l-.4.3-1.4 1.3c-.3.4-.4 1-.4 1.6 0 0 0 .2.2.2h1.5c.2 0 .2-.1.2-.2l.1-.7.5-.7.6-.4z"
        />
      </svg>
    ),
    arrow: (
      <svg width={26} height={26}>
        <path
          fill="hsl(206,100%,52%)"
          d="M12 .7a2 2 0 013 0l8.5 9.6a1 1 0 01-.7 1.7H4.2a1 1 0 01-.7-1.7L12 .7z"
        />
        <path
          opacity=".5"
          fill="hsl(206,100%,52%)"
          d="M20.6 16H6.4l7.1 8 7-8zM15 25.3a2 2 0 01-3 0l-8.5-9.6a1 1 0 01.7-1.7h18.6a1 1 0 01.7 1.7L15 25.3z"
        />
      </svg>
    ),
    tag: (
      <svg width={26} height={26}>
        <path
          fill="hsl(206,100%,52%)"
          d="M14.8 3a2 2 0 00-1.4.6l-10 10a2 2 0 000 2.8l8.2 8.2c.8.8 2 .8 2.8 0l10-10c.4-.4.6-.9.6-1.4V5a2 2 0 00-2-2h-8.2zm5.2 7a2 2 0 110-4 2 2 0 010 4z"
        />
        <path
          opacity=".5"
          fill="hsl(206,100%,52%)"
          d="M13 0a2 2 0 00-1.4.6l-10 10a2 2 0 000 2.8c.1-.2.3-.6.6-.8l10-10a2 2 0 011.4-.6h9.6a2 2 0 00-2-2H13z"
        />
      </svg>
    ),
    trophy: (
      <svg width={26} height={26}>
        <path
          fill="hsl(206,100%,52%)"
          d="M21 4V2H5v2H1v5c0 2 2 4 4 4v1c0 2.5 3 4 7 4v3H7s-1.2 2.3-1.2 3h14.4c0-.6-1.2-3-1.2-3h-5v-3c4 0 7-1.5 7-4v-1c2 0 4-2 4-4V4h-4zM5 11c-1 0-2-1-2-2V6h2v5zm11.5 2.7l-3.5-2-3.5 1.9L11 9.8 7.2 7.5h4.4L13 3.8l1.4 3.7h4L15.3 10l1.4 3.7h-.1zM23 9c0 1-1 2-2 2V6h2v3z"
        />
      </svg>
    ),
    serach: (
      <svg width={18} height={18} viewBox="0 0 18 18">
        <path
          fill="hsl(210, 8%, 70%)"
          d="m18 16.5-5.14-5.18h-.35a7 7 0 1 0-1.19 1.19v.35L16.5 18l1.5-1.5ZM12 7A5 5 0 1 1 2 7a5 5 0 0 1 10 0Z"
        />
      </svg>
    ),
    earth: (
      <svg width={18} height={18} viewBox="0 0 18 18">
        <path
          fill="hsl(210,8%,5%)"
          d="M9 1C4.64 1 1 4.64 1 9c0 4.36 3.64 8 8 8 4.36 0 8-3.64 8-8 0-4.36-3.64-8-8-8ZM8 15.32a6.46 6.46 0 0 1-4.3-2.74 6.46 6.46 0 0 1-.93-5.01L7 11.68v.8c0 .88.12 1.32 1 1.32v1.52Zm5.72-2c-.2-.66-1-1.32-1.72-1.32h-1v-2c0-.44-.56-1-1-1H6V7h1c.44 0 1-.56 1-1V5h2c.88 0 1.4-.72 1.4-1.6v-.33a6.45 6.45 0 0 1 3.83 4.51 6.45 6.45 0 0 1-1.51 5.73v.01Z"
        />
      </svg>
    ),
    star: (
      <svg width={18} height={18} viewBox="0 0 18 18">
        <path
          fill="hsl(27,90%,55%)"
          d="M9.86.89a1.14 1.14 0 0 0-1.72 0l-.5.58c-.3.35-.79.48-1.23.33l-.72-.25a1.14 1.14 0 0 0-1.49.85l-.14.76c-.1.45-.45.8-.9.9l-.76.14c-.67.14-1.08.83-.85 1.49l.25.72c.15.44.02.92-.33 1.23l-.58.5a1.14 1.14 0 0 0 0 1.72l.58.5c.35.3.48.79.33 1.23l-.25.72c-.23.66.18 1.35.85 1.49l.76.14c.45.1.8.45.9.9l.14.76c.14.67.83 1.08 1.49.85l.72-.25c.44-.15.92-.02 1.23.33l.5.58c.46.52 1.26.52 1.72 0l.5-.58c.3-.35.79-.48 1.23-.33l.72.25c.66.23 1.35-.18 1.49-.85l.14-.76c.1-.45.45-.8.9-.9l.76-.14c.67-.14 1.08-.83.85-1.49l-.25-.72c-.15-.44-.02-.92.33-1.23l.58-.5c.52-.46.52-1.26 0-1.72l-.58-.5c-.35-.3-.48-.79-.33-1.23l.25-.72a1.14 1.14 0 0 0-.85-1.49l-.76-.14c-.45-.1-.8-.45-.9-.9l-.14-.76a1.14 1.14 0 0 0-1.49-.85l-.72.25c-.44.15-.92.02-1.23-.33l-.5-.58Zm-.49 2.67L10.6 6.6c.05.15.19.24.34.25l3.26.22c.36.03.5.48.23.71l-2.5 2.1a.4.4 0 0 0-.14.4l.8 3.16a.4.4 0 0 1-.6.44L9.2 12.13a.4.4 0 0 0-.42 0l-2.77 1.74a.4.4 0 0 1-.6-.44l.8-3.16a.4.4 0 0 0-.13-.4l-2.5-2.1a.4.4 0 0 1 .22-.7l3.26-.23a.4.4 0 0 0 .34-.25l1.22-3.03a.4.4 0 0 1 .74 0Z"
        />
      </svg>
    ),
    basket: (
      <svg width={14} height={14} viewBox="0 0 14 14">
        <path
          fill="#fdfdfd"
          d="M4 3a1 1 0 0 1 1-1h4a1 1 0 0 1 1 1v1h.5c.83 0 1.5.67 1.5 1.5v5c0 .83-.67 1.5-1.5 1.5h-7A1.5 1.5 0 0 1 2 10.5v-5C2 4.67 2.67 4 3.5 4H4V3Zm5 1V3H5v1h4Z"
        />
      </svg>
    ),
    shield: (
      <svg width={9} height={10} viewBox="0 0 9 10">
        <path
          fill="#fdfdfd"
          d="M0 1.84 4.5 0 9 1.84v3.17C9 7.53 6.3 10 4.5 10 2.7 10 0 7.53 0 5.01V1.84Z"
        />
        <path
          fill="hsl(210, 8%, 45%)"
          d="M1 2.5 4.5 1 8 2.5v2.51C8 7.34 5.34 9 4.5 9 3.65 9 1 7.34 1 5.01V2.5Zm2.98 3.02L3.2 7h2.6l-.78-1.48a.4.4 0 0 1 .15-.38c.34-.24.73-.7.73-1.14 0-.71-.5-1.23-1.41-1.23-.92 0-1.39.52-1.39 1.23 0 .44.4.9.73 1.14.12.08.18.23.15.38Z"
        />
      </svg>
    ),
    notFound: (
      <svg width={196} height={196} viewBox="0 0 196 196">
        <path
          opacity=".07"
          d="M143.05 18.44C100.21 4.5 55.18 11.8 30.5 34.5c-25 23-27.88 56.81-24 71.5 3.88 14.69 20.34 65 49.5 71s90.25 14.13 121.01-22.8c30.76-36.92 3.69-123.5-33.96-135.76Zm-35.88 14.71 58.13 100.7a9.43 9.43 0 0 1-8.17 14.15H40.87a9.43 9.43 0 0 1-8.17-14.15l58.13-100.7a9.43 9.43 0 0 1 16.34 0Z"
        />
        <path
          opacity=".2"
          d="M111.44 41.15a7.43 7.43 0 0 0-12.88 0l-58.14 100.7A7.43 7.43 0 0 0 46.86 153h116.28c5.72 0 9.3-6.2 6.44-11.15l-58.14-100.7ZM109 126.5a9.5 9.5 0 1 1-19 0 9.5 9.5 0 0 1 19 0Zm-17-64a7.5 7.5 0 1 1 15 0v41a7.5 7.5 0 0 1-15 0v-41ZM58.82 39.93a3.5 3.5 0 0 0-1.4 4.74L62.49 54a3.5 3.5 0 0 0 6.15-3.35l-5.07-9.31a3.5 3.5 0 0 0-4.75-1.4ZM37.65 52.02a3.5 3.5 0 0 1 3.52-6.04L60.26 57.1a3.5 3.5 0 1 1-3.53 6.04L37.65 52.02Zm113.84 14.65a3.5 3.5 0 0 0-6.15-3.34l-5.08 9.31a3.5 3.5 0 0 0 6.15 3.35l5.08-9.32Zm21.03 2.57a3.5 3.5 0 0 1-1.26 4.78l-19.09 11.13a3.5 3.5 0 1 1-3.52-6.04l19.08-11.13a3.5 3.5 0 0 1 4.79 1.26Zm-3.32 26.28a3.5 3.5 0 0 1-3.9 3.05l-13.73-1.7a3.5 3.5 0 1 1 .86-6.95l13.72 1.7a3.5 3.5 0 0 1 3.05 3.9Zm-129.5-22a3.5 3.5 0 0 0 3.91 3.05l13.72-1.7a3.5 3.5 0 1 0-.85-6.95l-13.73 1.7a3.5 3.5 0 0 0-3.04 3.9Z"
        />
        <path d="m149.37 17 4.63 4.5-4.5 4.5-4.5-4.5 4.37-4.5Zm8.92 145 9.29 9.29-9.3 9.29-9.28-9.3 9.29-9.28Zm-3.1 9.29 3.1 3.1 3.1-3.1-3.1-3.1-3.1 3.1ZM99.5 136a9.5 9.5 0 1 0 0-19 9.5 9.5 0 0 0 0 19Zm0-4a5.5 5.5 0 1 1 0-11 5.5 5.5 0 0 1 0 11Zm0-77a7.5 7.5 0 0 0-7.5 7.5v41a7.5 7.5 0 0 0 15 0v-41a7.5 7.5 0 0 0-7.5-7.5ZM96 62.5a3.5 3.5 0 1 1 7 0v41a3.5 3.5 0 1 1-7 0v-41Zm-5.17-29.35a9.43 9.43 0 0 1 16.34 0l58.13 100.7a9.43 9.43 0 0 1-8.17 14.15H40.87a9.43 9.43 0 0 1-8.17-14.15l58.13-100.7Zm12.87 2a5.43 5.43 0 0 0-9.4 0l-58.13 100.7a5.43 5.43 0 0 0 4.7 8.15h116.26c4.18 0 6.8-4.53 4.7-8.15L103.7 35.15ZM13 112a2 2 0 0 1 2 2v4h4a2 2 0 1 1 0 4h-4v4a2 2 0 1 1-4 0v-4H7a2 2 0 1 1 0-4h4v-4c0-1.1.9-2 2-2Z" />
      </svg>
    ),
    questionCircleFill: (
      <svg
        xmlns="http://www.w3.org/2000/svg"
        width="20"
        height="20"
        fill="currentColor"
        className="bi bi-question-circle-fill"
        viewBox="0 0 16 16"
        id="IconChangeColor"
      >
        {' '}
        <path
          d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM5.496 6.033h.825c.138 0 .248-.113.266-.25.09-.656.54-1.134 1.342-1.134.686 0 1.314.343 1.314 1.168 0 .635-.374.927-.965 1.371-.673.489-1.206 1.06-1.168 1.987l.003.217a.25.25 0 0 0 .25.246h.811a.25.25 0 0 0 .25-.25v-.105c0-.718.273-.927 1.01-1.486.609-.463 1.244-.977 1.244-2.056 0-1.511-1.276-2.241-2.673-2.241-1.267 0-2.655.59-2.75 2.286a.237.237 0 0 0 .241.247zm2.325 6.443c.61 0 1.029-.394 1.029-.927 0-.552-.42-.94-1.029-.94-.584 0-1.009.388-1.009.94 0 .533.425.927 1.01.927z"
          id="mainIconPathAttribute"
          fill="#737373"
        ></path>{' '}
      </svg>
    ),
  };
  return IconType[name];
}

export default SvgIcon;
