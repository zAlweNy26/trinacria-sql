package sc.alwe.trinacriasql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sc.alwe.trinacriasql.model.TrinacriaSqlQueryResult;

public class TestUpdate {

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
	public void testUpdateAll() throws SQLException {
		TrinacriaSqlInterpreter gsi = new TrinacriaSqlInterpreter(connection);
		TrinacriaSqlQueryResult result = gsi.execute("rifacimm city accunza city_name accuss� 'NAPULI', city_id accuss� 10");
		Assert.assertEquals((Integer) 4, result.getAffectedRows());
		
		result = gsi.execute("ripigliammo tutto chillo ch'era 'o nuostro mmiez 'a city");
		ResultSet resultSet = result.getResultSet();
		int counter = 0;
		while (resultSet.next()) {
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			Assert.assertEquals(10, id);
			Assert.assertEquals("NAPULI", name);
			counter++;
		}
		Assert.assertEquals(4, counter);
	}
	
	@Test
	public void testUpdateWhere() throws SQLException {
		TrinacriaSqlInterpreter gsi = new TrinacriaSqlInterpreter(connection);
		TrinacriaSqlQueryResult result = gsi.execute("rifacimm city accunza city_name accuss� 'NAPULI', city_id accuss� 10 ar� city_id = 1");
		Assert.assertEquals((Integer) 1, result.getAffectedRows());
		
		result = gsi.execute("ripigliammo tutto chillo ch'era 'o nuostro mmiez 'a city ar� city_id = 10");
		ResultSet resultSet = result.getResultSet();
		int counter = 0;
		while (resultSet.next()) {
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			Assert.assertEquals(10, id);
			Assert.assertEquals("NAPULI", name);
			counter++;
		}
		Assert.assertEquals(1, counter);
	}

}