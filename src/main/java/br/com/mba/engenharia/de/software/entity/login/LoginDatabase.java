package br.com.mba.engenharia.de.software.entity.login;

public class LoginDatabase implements Login{
    @Override
    public boolean verifyCredentials(String user, String password) {
        if((user.contentEquals("admin")) && (password.contentEquals("123456"))){
            return true;
        }
        else{
            return false;
        }
    }
}
