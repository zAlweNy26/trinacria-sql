package sc.alwe.trinacriasql.states.query;

import sc.alwe.trinacriasql.Keywords;
import sc.alwe.trinacriasql.model.ChiFariException;
import sc.alwe.trinacriasql.model.QueryInfo;
import sc.alwe.trinacriasql.states.AbstractState;
import sc.alwe.trinacriasql.states.AnyTokenConsumerState;
import sc.alwe.trinacriasql.states.CommaSeparedValuesWithAliasState;
import sc.alwe.trinacriasql.states.GreedyMatchKeywordState;

/**
 * State that allows a select to switch between the * operator and the column
 * names to rietrieve.
 */
public class SelectColumnsState extends AbstractState {

	public SelectColumnsState(QueryInfo queryInfo) {
		super(queryInfo);
	}

	@Override
	public AbstractState transitionToNextState(String token) throws ChiFariException {
		if (token.equalsIgnoreCase(Keywords.ASTERISK_KEYWORDS[0])) {
			// Token is "*" (all columns). We proceed to from keyword
			queryInfo.addColumnName("*");
			return new GreedyMatchKeywordState(queryInfo, Keywords.ASTERISK_KEYWORDS,
					q -> new GreedyMatchKeywordState(q, Keywords.FROM_KEYWORDS,
							q2 -> new AnyTokenConsumerState(q2, q2::setTableName, OptionalWhereState::new), 0));
		} else {
			// Token is a column name, we continue until there are none
			queryInfo.addColumnName(token);
			return new CommaSeparedValuesWithAliasState(queryInfo, queryInfo.getColumnNames(), Keywords.FROM_KEYWORDS[0],	"%COLUMN_NAME%", 
                    q -> new GreedyMatchKeywordState(queryInfo, Keywords.FROM_KEYWORDS,
							q2 -> new AnyTokenConsumerState(q2, q2::setTableName, OptionalWhereState::new)));
		}
	}
}