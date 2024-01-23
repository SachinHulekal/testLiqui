package testLiqui;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
public class testClass {
	public String handleRequest(Context context) {
		LambdaLogger logger = context.getLogger();
		logger.log("Invoked JDBCSample.getCurrentTime");

		String currentTime = "unavailable";

		// Get time from DB server
		try {

			Properties props = new Properties();
			InputStream fis = testClass.class.getClassLoader()
					.getResourceAsStream("C:/test/testLiqui/src/resources/db.properties");
			props.load(fis);
			fis.close();

			String url = props.getProperty("url");
			String username = props.getProperty("username");
			String password = props.getProperty("password");

			System.out.println(password);
			System.out.println(username);
			System.out.println(url);

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
			e.printStackTrace();
			logger.log("Caught exception: " + e.getMessage());
		}

		return currentTime;

	}
}