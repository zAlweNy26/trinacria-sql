package sc.alwe.trinacriasql.states.where;

import sc.alwe.trinacriasql.model.ChiFariException;
import sc.alwe.trinacriasql.model.QueryInfo;
import sc.alwe.trinacriasql.model.WhereCondition;
import sc.alwe.trinacriasql.states.AbstractState;

/**
 * State for creating a new WHERE subclause in the format "field operator
 * value" by inserting the field name.
 */
public class WhereFieldState extends AbstractState {

	public WhereFieldState(QueryInfo queryInfo) {
		super(queryInfo);
	}

	@Override
	public AbstractState transitionToNextState(String token) throws ChiFariException {
		return new WhereOperatorState(queryInfo, new WhereCondition(token));
	}

}