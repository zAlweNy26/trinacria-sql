package sc.alwe.trinacriasql.states.where;

import java.util.Arrays;

import sc.alwe.trinacriasql.Keywords;
import sc.alwe.trinacriasql.model.ChiFariException;
import sc.alwe.trinacriasql.model.QueryInfo;
import sc.alwe.trinacriasql.states.AbstractState;

/**
 * State for joining two WHERE subclause using an AND or OR operator, or
 * finishing the WHERE clause.
 * 
 * @author Daniele Nicosia
 *
 */
public class WhereJoinState extends AbstractState {

	public WhereJoinState(QueryInfo queryInfo) {
		super(queryInfo);
	}

	@Override
	public AbstractState transitionToNextState(String token) throws ChiFariException {
		if (token.equalsIgnoreCase(Keywords.AND_KEYWORD)) {
			queryInfo.addWhereConditionsJoinOperator("AND");
			return new WhereFieldState(queryInfo);
		}
		if (token.equalsIgnoreCase(Keywords.OR_KEYWORD)) {
			queryInfo.addWhereConditionsJoinOperator("OR");
			return new WhereFieldState(queryInfo);
		}
		throw new ChiFariException(Arrays.asList(Keywords.AND_KEYWORD, Keywords.OR_KEYWORD), token);
	}

	@Override
	public boolean isFinalState() {
		return true;
	}

}