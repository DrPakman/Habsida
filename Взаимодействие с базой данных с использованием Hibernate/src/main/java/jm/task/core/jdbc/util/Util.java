package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;

import java.util.Properties;

public class Util {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties properties = new Properties();
                properties.setProperty(AvailableSettings.DRIVER, "com.mysql.cj.jdbc.Driver");
                properties.setProperty(AvailableSettings.URL, "jdbc:mysql://localhost:3306/Hometask_Hibernate");
                properties.setProperty(AvailableSettings.USER, "root");
                properties.setProperty(AvailableSettings.PASS, "12345678");
                properties.setProperty(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL8Dialect");

                StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(properties)
                        .build();

                MetadataSources metadataSources = new MetadataSources(standardRegistry);
                metadataSources.addAnnotatedClass(User.class);

                Metadata metadata = metadataSources.getMetadataBuilder().build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Unable to initialize Hibernate", e);
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
