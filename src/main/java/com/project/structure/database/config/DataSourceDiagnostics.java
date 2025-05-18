package com.project.structure.database.config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataSourceDiagnostics {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void printMetadata() {
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData meta = conn.getMetaData();
            System.out.println("JDBC Driver Name: " + meta.getDriverName());
            System.out.println("JDBC Driver Version: " + meta.getDriverVersion());
            System.out.println("DB Name: " + meta.getDatabaseProductName());
            System.out.println("DB Version: " + meta.getDatabaseProductVersion());
            System.out.println("Autocommit: " + conn.getAutoCommit());
            System.out.println("Isolation Level: " + conn.getTransactionIsolation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
