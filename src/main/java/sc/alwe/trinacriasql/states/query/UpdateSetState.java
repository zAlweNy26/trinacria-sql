package sc.alwe.trinacriasql.states.query;

import java.util.Arrays;

import sc.alwe.trinacriasql.Keywords;
import sc.alwe.trinacriasql.model.ChiFariException;
import sc.alwe.trinacriasql.model.QueryInfo;
import sc.alwe.trinacriasql.states.AbstractState;
import sc.alwe.trinacriasql.states.AnyTokenConsumerState;
import sc.alwe.trinacriasql.states.SingleTokenMatchState;
import sc.alwe.trinacriasql.states.where.WhereFieldState;

/**
 * State for an update when the first value is set. Allows to continue setting
 * values or moving on to an optional where clause.
 */
public class UpdateSetState extends AbstractState {

	public UpdateSetState(QueryInfo queryInfo) {
		super(queryInfo);
	}

	@Override
	public AbstractState transitionToNextState(String token) throws ChiFariException {
		// Adds another variable
		if (token.equalsIgnoreCase(",")) {
			return new AnyTokenConsumerState(queryInfo, queryInfo::addColumnName,
					q -> new SingleTokenMatchState(q, Keywords.SET_EQUAL_KEYWORD,
							q2 -> new AnyTokenConsumerState(q2, q2::addValue, UpdateSetState::new)));
		}
		// Moves to a where clause
		if (token.equalsIgnoreCase(Keywords.WHERE_KEYWORD)) {
			return new WhereFieldState(queryInfo);
		}
		throw new ChiFariException(Arrays.asList(",", Keywords.WHERE_KEYWORD, "%END_OF_QUERY%"), token);
	}

	@Override
	public boolean isFinalState() {
		return true;
	}

}