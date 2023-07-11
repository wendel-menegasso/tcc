package br.com.mba.engenharia.de.software;

import com.mysql.cj.conf.DatabaseUrlContainer;

public class DatabaseUrlContainerImpl implements DatabaseUrlContainer {
    @Override
    public String getDatabaseUrl() {
        return "jdbc:mysql://localhost/tcc";
    }
}
