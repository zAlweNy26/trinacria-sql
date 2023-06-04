package sc.alwe.trinacriasql.states;

import java.util.function.Function;

import sc.alwe.trinacriasql.model.ChiFariException;
import sc.alwe.trinacriasql.model.QueryInfo;

/**
 * States that proceeds if a specific token is matched exactly.
 */
public class SingleTokenMatchState extends AbstractState {

	private String expectedToken;
	private Function<QueryInfo, AbstractState> transitionFunction;

	public SingleTokenMatchState(QueryInfo queryInfo, String expectedToken,
			Function<QueryInfo, AbstractState> transitionFunction) {
		super(queryInfo);
		this.expectedToken = expectedToken;
		this.transitionFunction = transitionFunction;
	}

	@Override
	public AbstractState transitionToNextState(String token) throws ChiFariException {
		if (token.equalsIgnoreCase(expectedToken)) {
			return transitionFunction.apply(queryInfo);
		}
		throw new ChiFariException(expectedToken, token);
	}

}