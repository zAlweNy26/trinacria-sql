package sc.alwe.trinacriasql.states;

import java.util.function.Function;

import sc.alwe.trinacriasql.model.ChiFariException;
import sc.alwe.trinacriasql.model.QueryInfo;

/**
 * State that proceeds to the next one if all the given keywords are matched,
 * otherwise throws an exception.
 * 
 * @author Daniele Nicosia
 *
 */
public class GreedyMatchKeywordState extends AbstractState {

	private int currentIndex = 1;
	private final String[] keywords;
	private final Function<QueryInfo, AbstractState> nextStateTransition;

	public GreedyMatchKeywordState(QueryInfo queryInfo, String[] keywords,
			Function<QueryInfo, AbstractState> nextStateTransition) {
		super(queryInfo);
		this.keywords = keywords;
		this.nextStateTransition = nextStateTransition;
	}

	public GreedyMatchKeywordState(QueryInfo queryInfo, String[] keywords,
			Function<QueryInfo, AbstractState> nextStateTransition, int currentIndex) {
		super(queryInfo);
		this.keywords = keywords;
		this.nextStateTransition = nextStateTransition;
		this.currentIndex = currentIndex;
	}

	@Override
	public AbstractState transitionToNextState(String token) throws ChiFariException {
		if (token.equalsIgnoreCase(keywords[currentIndex])) {
			currentIndex++;
			if (currentIndex == keywords.length) {
				return nextStateTransition.apply(queryInfo);
			}
			return this;
		}
		throw new ChiFariException(keywords[currentIndex], token);
	}
}