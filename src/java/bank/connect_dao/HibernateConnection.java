package bank.connect_dao;

import java.util.Properties;
import bank.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateConnection {
    private static SessionFactory factory;    
    private static final String DIALECT="org.hibernate.dialect.MySQL5Dialect";
    private static final String USERNAME="root";
    private static final String URL="jdbc:mysql://localhost:3306/ignou?useSSL=false";
    private static final String DRIVER="com.mysql.jdbc.Driver";
    private static final String PASSWORD="17121999";
    private static final String HBM2DDL="update";
    private static final String SHOWSQL="true";
    
    static {
        Configuration config=new Configuration();
            Properties settings = new Properties();
            
                settings.put(Environment.DIALECT, DIALECT);
                settings.put(Environment.DRIVER, DRIVER);
                settings.put(Environment.URL, URL);
                settings.put(Environment.USER, USERNAME);
                settings.put(Environment.PASS, PASSWORD);
                settings.put(Environment.HBM2DDL_AUTO, HBM2DDL);
                settings.put(Environment.SHOW_SQL, SHOWSQL);
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                config.addProperties(settings);
                config.addAnnotatedClass(Customer.class);
                config.addAnnotatedClass(Manager.class);
                config.addAnnotatedClass(Employee.class);
                config.addAnnotatedClass(IFSCCode.class);
                config.addAnnotatedClass(Transactions.class);
                
                ServiceRegistry sr=new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
                factory=config.buildSessionFactory(sr);
    }
    
    public static SessionFactory createFactory(){
        return factory;
    }
    
}
