package sc.alwe.trinacriasql.states;

import java.util.function.Consumer;
import java.util.function.Function;

import sc.alwe.trinacriasql.model.ChiFariException;
import sc.alwe.trinacriasql.model.QueryInfo;

/**
 * Consumes any token applying the given function and then moves to the next
 * state.
 * 
 * @author Daniele Nicosia
 *
 */
public class AnyTokenConsumerState extends AbstractState {

	private final Consumer<String> tokenConsumer;
	private final Function<QueryInfo, AbstractState> transitionFunction;

	public AnyTokenConsumerState(QueryInfo queryInfo, Consumer<String> tokenConsumer,
			Function<QueryInfo, AbstractState> transitionFunction) {
		super(queryInfo);
		this.tokenConsumer = tokenConsumer;
		this.transitionFunction = transitionFunction;
	}

	@Override
	public AbstractState transitionToNextState(String token) throws ChiFariException {
		tokenConsumer.accept(token);
		return transitionFunction.apply(queryInfo);
	}

}