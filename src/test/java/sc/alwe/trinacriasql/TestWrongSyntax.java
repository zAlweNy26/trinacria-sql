package sc.alwe.trinacriasql;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sc.alwe.trinacriasql.model.ChiFariException;

public class TestWrongSyntax {
	
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
	public void testWrongInitialKeyword() throws SQLException {
		TrinacriaSqlInterpreter gsi = new TrinacriaSqlInterpreter(connection);
		gsi.execute("wella");
	}
	
	@Test(expected = ChiFariException.class)
	public void testSelectDoubleComma() throws SQLException {
		TrinacriaSqlInterpreter gsi = new TrinacriaSqlInterpreter(connection);
		gsi.execute("pigghiamu a , ,");
	}
	
	@Test(expected = ChiFariException.class)
	public void testSelectWrongFrom() throws SQLException {
		TrinacriaSqlInterpreter gsi = new TrinacriaSqlInterpreter(connection);
		gsi.execute("pigghiamu a chisti");
	}
	
	@Test(expected = ChiFariException.class)
	public void testQueryEndExpected() throws SQLException {
		TrinacriaSqlInterpreter gsi = new TrinacriaSqlInterpreter(connection);
		gsi.execute("turnamu nnarrè wrong");
	}
	
	@Test(expected = ChiFariException.class)
	public void testWrongLongOperator() throws SQLException {
		TrinacriaSqlInterpreter gsi = new TrinacriaSqlInterpreter(connection);
		gsi.execute("pigghiamu tutto proprio tutto");
	}
	
	@Test(expected = ChiFariException.class)
	public void testIncompleteQuery() throws SQLException {
		TrinacriaSqlInterpreter gsi = new TrinacriaSqlInterpreter(connection);
		gsi.execute("pigghiamu");
	}
	
	@Test(expected = ChiFariException.class)
	public void testWhereInvalidOperator() throws SQLException {
		TrinacriaSqlInterpreter gsi = new TrinacriaSqlInterpreter(connection);
		gsi.execute("pigghiamu tuttu chiddu chi cc'è chi veni da city unni city_id !! 4");
	}
	
	@Test(expected = ChiFariException.class)
	public void testWrongSelectWhereJoinOperator() throws SQLException {
		TrinacriaSqlInterpreter gsi = new TrinacriaSqlInterpreter(connection);
		gsi.execute("pigghiamu tuttu chiddu chi cc'è chi veni da city unni city_id <= 4 ma 5 > 1");
	}
	
	@Test(expected = ChiFariException.class)
	public void testWrongSelectWhereClause() throws SQLException {
		TrinacriaSqlInterpreter gsi = new TrinacriaSqlInterpreter(connection);
		gsi.execute("pigghiamu tuttu chiddu chi cc'è chi veni da city quando city_id <= 4 e 5 > 1");
	}
	
	@Test(expected = ChiFariException.class)
	public void testWrongUpdateSetClause() throws SQLException {
		TrinacriaSqlInterpreter gsi = new TrinacriaSqlInterpreter(connection);
		gsi.execute("refacìemu city cambia city_id = 1");
	}
	
	@Test(expected = ChiFariException.class)
	public void testWrongUpdateWhereClause() throws SQLException {
		TrinacriaSqlInterpreter gsi = new TrinacriaSqlInterpreter(connection);
		gsi.execute("refacìemu city mètti city_id accussì 1 e basta");
	}
}