package refactoring;

import com.mysql.cj.conf.DatabaseUrlContainer;

public class DatabaseUrlContainerImpl implements DatabaseUrlContainer {
    @Override
    public String getDatabaseUrl() {
        return "jdbc:mysql://localhost/tcc";
    }
}
