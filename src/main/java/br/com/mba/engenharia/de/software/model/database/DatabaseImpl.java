package br.com.mba.engenharia.de.software.model.database;


import br.com.mba.engenharia.de.software.model.login.LoginDatabase;

public class DatabaseImpl implements Database {

    private LoginDatabase loginDatabase;

    @Override
    public boolean isConnected(String user, String password) {
        return false;
    }

    @Override
    public boolean connect(String user, String password) throws DatabaseException {
        loginDatabase = new LoginDatabase();
        if(loginDatabase.verifyCredentials(user,password)){
            System.out.println("Correct password");
            return true;
        }
        else{
            throw new DatabaseException("Wrong password");
        }
    }

    @Override
    public void disconnect(String user, String password) {

    }
}
