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
			String url = "jdbc:postgresql://database-1.c9yewug28hvf.ap-south-1.rds.amazonaws.com:5434/postgres";
			String username = "postgres";
			String password = "postgres";

			Connection conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery("SELECT NOW()");

			if (resultSet.next()) {
				currentTime = resultSet.getObject(1).toString();
			}

			logger.log("Successfully executed query.  Result: " + currentTime);

		} catch (Exception e) {
			e.printStackTrace();
			logger.log("Caught exception: " + e.getMessage());
		}

		return currentTime;

	}
}