export const debugMode = false;

export function checkLoginStatus(){
  const userId = sessionStorage.getItem("userid");
  const login = sessionStorage.getItem("login");
  const email = sessionStorage.getItem("email");
  console.log("Login info> userId: "+userId+" login: "+login+" email: "+email)
  console.log("Login info type> userId: "+typeof(userId)+" login: "+typeof(login)+" email: "+typeof(email))
  console.log("Login info null?> userId: "+(Object.is(userId,null))+" login: "+(login === null)+" email: "+(email === null))

  if (userId === null || login === null || email === null){
    return false
  }
  if ((userId.length > 0) && (login.length > 0) && (email.length > 0)){
    return true
  }
  return false
}

export function logoutFunction(){
  // sessionStorage.setItem("userid", null);
  // sessionStorage.setItem("login", null);
  // sessionStorage.setItem("email", null);
  sessionStorage.removeItem("userid");
  sessionStorage.removeItem("login");
  sessionStorage.removeItem("email");
  checkLoginStatus()
}