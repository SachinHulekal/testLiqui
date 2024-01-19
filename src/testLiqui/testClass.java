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
		LambdaLogger logger = context.getLogger();
		logger.log("EVENT TYPE: " + event.getClass());
		return null;
	}
}