package testLiqui;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import java.util.Properties;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.FileSystemResourceAccessor;

// Handler value: example.HandlerInteger
public class testClass implements RequestHandler<Map<String,String>, String>{
  /*
   * Takes in an InputRecord, which contains two integers and a String.
   * Logs the String, then returns the sum of the two Integers.
   */
	private static final String CHANGELOG_PATH = "C:/test/testLiqui/src/resource/changelog.sql";
	
	@Override
	public String handleRequest(Map<String,String> event, Context context) {
		try {
			Properties props = new Properties();
			FileInputStream fis = new FileInputStream("C:/test/testLiqui/src/resource/db.properties");
			props.load(fis);
			fis.close();
            
			String url = props.getProperty("url");
			String username = props.getProperty("username");
			String password = props.getProperty("password");

			Connection con = DriverManager.getConnection(url, username, password);
			System.out.println(con);

	        Database database = DatabaseFactory.getInstance()
	                .findCorrectDatabaseImplementation(new JdbcConnection(con));
	        Liquibase liquibase = new Liquibase( 
	        		"C:/Users/HulekalS03/Downloads/eclipse-java-2022-12-R-win32-x86_64/eclipse/com.test/src/main/resources/changelog.sql", 
	        	    new FileSystemResourceAccessor(), 
	        	    database 
	        	);
	        liquibase.update( "" );
	       
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		

	}
}
