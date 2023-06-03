package sc.alwe.trinacriasql.states.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sc.alwe.trinacriasql.Keywords;
import sc.alwe.trinacriasql.model.ChiFariException;
import sc.alwe.trinacriasql.model.QueryInfo;
import sc.alwe.trinacriasql.model.QueryInfo.QueryType;
import sc.alwe.trinacriasql.states.AbstractState;
import sc.alwe.trinacriasql.states.AnyTokenConsumerState;
import sc.alwe.trinacriasql.states.GreedyMatchKeywordState;
import sc.alwe.trinacriasql.states.where.WhereFieldState;

/**
 * State that allows for an optional WHERE clause, JOIN clause (only when the
 * query is a select) or end of the query.
 * 
 * @author Daniele Nicosia
 *
 */
public class OptionalWhereState extends AbstractState {

	public OptionalWhereState(QueryInfo queryInfo) {
		super(queryInfo);
	}

	@Override
	public AbstractState transitionToNextState(String token) throws ChiFariException {
		List<String> expectedKeywords = new ArrayList<>(Arrays.asList(Keywords.WHERE_KEYWORD));
		if (token.equalsIgnoreCase(Keywords.WHERE_KEYWORD)) {
			return new WhereFieldState(queryInfo);
		}
		if (queryInfo.getType().equals(QueryType.SELECT)) {
			expectedKeywords.add(Keywords.JOIN_KEYWORDS[0]);
			if (token.equalsIgnoreCase(Keywords.JOIN_KEYWORDS[0])) {
				return new GreedyMatchKeywordState(queryInfo, Keywords.JOIN_KEYWORDS,
						q -> new AnyTokenConsumerState(q, q::addJoinedTable, OptionalWhereState::new));
			}
		}
		throw new ChiFariException(expectedKeywords, token);
	}

	@Override
	public boolean isFinalState() {
		return true;
	}

}