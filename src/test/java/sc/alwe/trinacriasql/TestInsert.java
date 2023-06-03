package sc.alwe.trinacriasql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sc.alwe.trinacriasql.model.TrinacriaSqlQueryResult;

public class TestInsert {

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
	public void testInsert() throws SQLException {
		TrinacriaSqlInterpreter gsi = new TrinacriaSqlInterpreter(connection);
		TrinacriaSqlQueryResult result = gsi.execute("nzipp 'ngoppa city chist 6, 'RHO'");
		Assert.assertEquals((Integer) 1, result.getAffectedRows());
		
		result = gsi.execute("ripigliammo tutto chillo ch'era 'o nuostro mmiez 'a city ar� city_id = 6 e city_name = 'RHO'");
		ResultSet resultSet = result.getResultSet();
		int counter = 0;
		while (resultSet.next()) {
			counter++;
		}
		Assert.assertEquals(1, counter);
	}
	
	@Test
	public void testInsertWithColumnNames() throws SQLException {
		TrinacriaSqlInterpreter gsi = new TrinacriaSqlInterpreter(connection);
		TrinacriaSqlQueryResult result = gsi.execute("nzipp 'ngoppa city city_name chist 'RHO'");
		Assert.assertEquals((Integer) 1, result.getAffectedRows());
		
		result = gsi.execute("ripigliammo tutto chillo ch'era 'o nuostro mmiez 'a city ar� city_id � nisciun e city_name = 'RHO'");
		ResultSet resultSet = result.getResultSet();
		int counter = 0;
		while (resultSet.next()) {
			counter++;
		}
		Assert.assertEquals(1, counter);
	}

}