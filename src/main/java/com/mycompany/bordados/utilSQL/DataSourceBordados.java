package com.mycompany.bordados.utilSQL;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Named
@RequestScoped
public class DataSourceBordados {

	private Connection connection;
	private DataSource ds;
	
	@PostConstruct
	public void afterCreate() {
		InitialContext ic;
		try {
			ic= new InitialContext();
			ds= (DataSource) ic.lookup("java:jboss/datasources/BORDADOS");
		}catch(NamingException e){
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		try {
			connection = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}


