package sc.alwe.trinacriasql;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

import sc.alwe.trinacriasql.model.ChiFariException;
import sc.alwe.trinacriasql.model.TrinacriaSqlQueryResult;

/**
 * Shell wrapper on the {@link TrinacriaSqlInterpreter}.
 * 
 * @author Daniele Nicosia
 *
 */
public class TrinacriaSqlShell {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in, StandardCharsets.ISO_8859_1.name());
		System.out.print("Insert a JDBC string to connect to a database: ");
		String url = scanner.nextLine();
		Connection connection = DriverManager.getConnection(url);
		TrinacriaSqlInterpreter trinacriaSqlParser = new TrinacriaSqlInterpreter(connection);

		System.out.println("Succesfully connected to DB");
		while (true) {
			try {
				System.out.print("> ");
				String query = scanner.nextLine();
				System.out.println(query);
				TrinacriaSqlQueryResult result = trinacriaSqlParser.execute(query);
				if (result.getResultSet() != null) {
					printSelectResult(result.getResultSet());
				}
				if (result.getAffectedRows() != null) {
					System.out.println("Affected rows: " + result.getAffectedRows());
				}
				if (result.getResultSet() == null && result.getAffectedRows() == null) {
					System.out.println("OK");
				}
			} catch (ChiFariException | SQLException e) {
				scanner.close();
				System.err.println("Error: " + e.getMessage());
			}
		}
	}

	private static void printSelectResult(ResultSet result) throws SQLException {
		ResultSetMetaData rsmd = result.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		for (int i = 1; i <= columnsNumber; i++) {
			System.out.print(rsmd.getColumnName(i) + " | ");
		}
		System.out.println();
		while (result.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				System.out.print(result.getString(i) + " | ");
			}
			System.out.println();
		}
	}

}