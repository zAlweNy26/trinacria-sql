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
		gsi.execute("we compà");
		gsi.execute("mìettemu rintra city chisti 6, 'RHO'");
		TrinacriaSqlQueryResult result = gsi
				.execute("pigghiamu tuttu chiddu chi cc'è chi veni da city unni city_id = 6");
		ResultSet resultSet = result.getResultSet();
		int expectedCount = 1;
		while (resultSet.next()) {
			expectedCount++;
		}
		Assert.assertEquals(expectedCount, 2);

		gsi.execute("turnamu nnarrè");
		result = gsi.execute("pigghiamu tuttu chiddu chi cc'è chi veni da city unni city_id = 6");
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
		gsi.execute("we compà");
		gsi.execute("mìettemu rintra city chisti 6, 'RHO'");
		TrinacriaSqlQueryResult result = gsi
				.execute("pigghiamu tuttu chiddu chi cc'è chi veni da city unni city_id = 6");
		ResultSet resultSet = result.getResultSet();
		int expectedCount = 1;
		while (resultSet.next()) {
			expectedCount++;
		}
		Assert.assertEquals(expectedCount, 2);

		gsi.execute("finemula ccà");
		result = gsi.execute("pigghiamu tuttu chiddu chi cc'è chi veni da city unni city_id = 6");
		resultSet = result.getResultSet();
		expectedCount = 1;
		while (resultSet.next()) {
			expectedCount++;
		}
		Assert.assertEquals(expectedCount, 2);
	}
}