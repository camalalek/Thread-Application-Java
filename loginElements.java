package ChatWebServer;

class loginElements extends threadSelect{
    String loginid, nickname, password;

    public void loginEntry() {
        loginid = "";
        nickname = "";
        password = "";
    }
    
    //Getters and Setters for the login Object class
    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
