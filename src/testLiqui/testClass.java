package testLiqui;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import java.util.Properties;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.FileSystemResourceAccessor;

// Handler value: example.HandlerInteger
public class testClass implements RequestHandler<Map<String, String>, Void> {

	@Override
	public Void handleRequest(Map<String, String> event, Context context) {
		try {
			Properties props = new Properties();
			FileInputStream fis = new FileInputStream("C:/test/testLiqui/src/resources/db.properties");
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
					"C:/test/testLiqui/src/resources/db.properties/changelog.sql",
					new FileSystemResourceAccessor(),
					database);
			liquibase.update("");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}