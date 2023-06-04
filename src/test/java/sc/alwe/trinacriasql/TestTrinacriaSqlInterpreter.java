package sc.alwe.trinacriasql;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sc.alwe.trinacriasql.model.ChiFariException;

public class TestTrinacriaSqlInterpreter {

	private Connection connection;

	@Before
	public void setup() throws SQLException {
		this.connection = Config.getDbConnection();
	}

	@After
	public void teardown() throws SQLException {
		this.connection.close();
	}

	@Test(expected = ChiFariException.class)
	public void testWrongDataTypeInsert() throws SQLException {
		TrinacriaSqlInterpreter gsi = new TrinacriaSqlInterpreter(connection);
		gsi.execute("mìettemu rintra city chisti 'RHO', 7");
	}

	@Test
	public void testSqlConversion() throws SQLException {
		String trinacriaQuery = "mìettemu rintra city chisti 6, 8";
		String sqlQuery = TrinacriaSqlInterpreter.toSqlQuery(trinacriaQuery);
		Assert.assertEquals("INSERT INTO city VALUES ( 6, 8 )", sqlQuery);
	}

}
