
export const loginDetails = (TOKEN_KEY, USER_ID, USER_NAME) => {
  localStorage.setItem('TOKEN_KEY', TOKEN_KEY);
  localStorage.setItem('USER_ID', USER_ID);
  localStorage.setItem('USER_NAME', USER_NAME);
}

export const logout = () => {
  localStorage.removeItem('TOKEN_KEY');
  localStorage.removeItem('USER_ID');
  localStorage.removeItem('USER_NAME');
  localStorage.removeItem('ACCOUNTS');
  localStorage.removeItem('IF_ACCOUNT_EXISTS');
}

export const isLogin = () => {
  if (localStorage.getItem('TOKEN_KEY')) {
      return true;
  }
 return false;
}
