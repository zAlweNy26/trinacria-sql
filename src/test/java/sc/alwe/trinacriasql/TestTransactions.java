package sc.alwe.trinacriasql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sc.alwe.trinacriasql.model.TrinacriaSqlQueryResult;

public class TestTransactions {

	private Connection connection;

	@Before
	public void setup() throws SQLException {
		this.connection = Config.getDbConnection();
	}

	@After
	public void teardown() throws SQLException {
		this.connection.close();
	}

	@Test
	public void testTransactionRollback() throws SQLException {
		TrinacriaSqlInterpreter gsi = new TrinacriaSqlInterpreter(connection);
		gsi.execute("ua uagli�");
		gsi.execute("nzipp 'ngoppa city chist 6, 'RHO'");
		TrinacriaSqlQueryResult result = gsi
				.execute("ripigliammo tutto chillo ch'era 'o nuostro mmiez 'a city ar� city_id = 6");
		ResultSet resultSet = result.getResultSet();
		int expectedCount = 1;
		while (resultSet.next()) {
			expectedCount++;
		}
		Assert.assertEquals(expectedCount, 2);

		gsi.execute("sfaccimm");
		result = gsi.execute("ripigliammo tutto chillo ch'era 'o nuostro mmiez 'a city ar� city_id = 6");
		resultSet = result.getResultSet();
		expectedCount = 1;
		while (resultSet.next()) {
			expectedCount++;
		}
		Assert.assertEquals(expectedCount, 1);
	}
	
	@Test
	public void testTransactionCommit() throws SQLException {
		TrinacriaSqlInterpreter gsi = new TrinacriaSqlInterpreter(connection);
		gsi.execute("ua uagli�");
		gsi.execute("nzipp 'ngoppa city chist 6, 'RHO'");
		TrinacriaSqlQueryResult result = gsi
				.execute("ripigliammo tutto chillo ch'era 'o nuostro mmiez 'a city ar� city_id = 6");
		ResultSet resultSet = result.getResultSet();
		int expectedCount = 1;
		while (resultSet.next()) {
			expectedCount++;
		}
		Assert.assertEquals(expectedCount, 2);

		gsi.execute("iamme bello ia'");
		result = gsi.execute("ripigliammo tutto chillo ch'era 'o nuostro mmiez 'a city ar� city_id = 6");
		resultSet = result.getResultSet();
		expectedCount = 1;
		while (resultSet.next()) {
			expectedCount++;
		}
		Assert.assertEquals(expectedCount, 2);
	}
}