package sc.alwe.trinacriasql.model;

import java.sql.ResultSet;

/**
 * Wrapper around JDBC query results, used by TrinacriaSQL.
 */
public class TrinacriaSqlQueryResult {
	
	private Integer affectedRows;
	
	private ResultSet resultSet;

	public Integer getAffectedRows() {
		return affectedRows;
	}

	public void setAffectedRows(Integer affectedRows) {
		this.affectedRows = affectedRows;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

}