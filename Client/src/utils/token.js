const TOKEN = 'token';

export function saveToken(token) {
  localStorage.setItem(TOKEN, token);
}

export function getToken() {
  return localStorage.getItem(TOKEN);
}

export function clearToken() {
  localStorage.clear();
}
export function parsedJwt(token) {
  let base64Url = token.split('.')[1];
  let base64 = decodeURIComponent(
    atob(base64Url)
      .split('')
      .map((c) => {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
      })
      .join('')
  );
  return JSON.parse(base64);
}
