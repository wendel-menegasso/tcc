package br.com.mba.engenharia.de.software.model.database;

public interface Database{
    boolean isConnected(String user, String password);
    boolean connect(String user, String password) throws DatabaseException;
    void disconnect(String user, String password);
}
