package br.com.mba.engenharia.de.software.model.database;

public class DatabaseException extends Exception{
    DatabaseException(String databaseException){
        super(databaseException);
    }
}
