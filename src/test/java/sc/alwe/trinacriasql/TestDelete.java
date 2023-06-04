package sc.alwe.trinacriasql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sc.alwe.trinacriasql.model.TrinacriaSqlQueryResult;

public class TestDelete {

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
	public void testDeleteAll() throws SQLException {
		TrinacriaSqlInterpreter gsi = new TrinacriaSqlInterpreter(connection);
		TrinacriaSqlQueryResult result = gsi.execute("livamu tuttu chi veni da city");
		Assert.assertEquals((Integer) 4, result.getAffectedRows());
		
		result = gsi.execute("pigghiamu tuttu chiddu chi cc'è chi veni da city");
		ResultSet resultSet = result.getResultSet();
		int counter = 0;
		while (resultSet.next()) {
			counter++;
		}
		Assert.assertEquals(0, counter);
	}
	
	@Test
	public void testDeleteWithWhere() throws SQLException {
		TrinacriaSqlInterpreter gsi = new TrinacriaSqlInterpreter(connection);
		TrinacriaSqlQueryResult result = gsi.execute("livamu tuttu chi veni da city unni city_id >= 2");
		Assert.assertEquals((Integer) 3, result.getAffectedRows());
		
		result = gsi.execute("pigghiamu tuttu chiddu chi cc'è chi veni da city");
		ResultSet resultSet = result.getResultSet();
		int counter = 0;
		while (resultSet.next()) {
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			Assert.assertEquals(1, id);
			Assert.assertEquals("MILANO", name);
			counter++;
		}
		Assert.assertEquals(1, counter);
	}

}