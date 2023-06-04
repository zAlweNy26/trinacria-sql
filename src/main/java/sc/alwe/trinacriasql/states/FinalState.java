package sc.alwe.trinacriasql.states;

import sc.alwe.trinacriasql.model.ChiFariException;
import sc.alwe.trinacriasql.model.QueryInfo;

/**
 * State that represents the end of a query.
 */
public class FinalState extends AbstractState {

	public FinalState(QueryInfo queryInfo) {
		super(queryInfo);
	}

	@Override
	public AbstractState transitionToNextState(String token) throws ChiFariException {
		throw new ChiFariException("%END_OF_QUERY%", token);
	}

	@Override
	public boolean isFinalState() {
		return true;
	}

}