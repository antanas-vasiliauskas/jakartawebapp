package com.example;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.enterprise.inject.Produces;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.io.InputStream;

@Startup
@Singleton
public class MyBatisSessionFactoryProvider {

    private SqlSessionFactory sqlSessionFactory;

    @PostConstruct
    public void init() {
        try {
            // Load MyBatis config
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            // Build factory using config
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // Optionally inject JNDI datasource (if not using XML datasource config)
            try {
                DataSource ds = (DataSource) new InitialContext().lookup("jdbc/UniversityDS");
                sqlSessionFactory.getConfiguration().setEnvironment(
                    new org.apache.ibatis.mapping.Environment(
                        "payara",
                        sqlSessionFactory.getConfiguration().getEnvironment().getTransactionFactory(),
                        ds
                    )
                );
            } catch (NamingException e) {
                throw new RuntimeException("Failed to lookup JNDI DataSource", e);
            }

        } catch (IOException e) {
            throw new RuntimeException("Could not load mybatis-config.xml", e);
        }
    }

    @Produces
    public SqlSessionFactory produceSessionFactory() {
        return sqlSessionFactory;
    }

    @Produces
    public SqlSession produceSession() {
        return sqlSessionFactory.openSession(true); // auto-commit
    }

    @PreDestroy
    public void cleanup() {
        // nothing to clean in default MyBatis SqlSessionFactory
    }
}
