package sc.alwe.trinacriasql.states.where;

import sc.alwe.trinacriasql.Keywords;
import sc.alwe.trinacriasql.model.ChiFariException;
import sc.alwe.trinacriasql.model.QueryInfo;
import sc.alwe.trinacriasql.model.WhereCondition;
import sc.alwe.trinacriasql.states.AbstractState;
import sc.alwe.trinacriasql.states.GreedyMatchKeywordState;

/**
 * State for continuing the last WHERE subclause in the format "field operator
 * value" by inserting the operator.
 * 
 * @author Daniele Nicosia
 *
 */
public class WhereOperatorState extends AbstractState {

	private WhereCondition condition;

	public WhereOperatorState(QueryInfo queryInfo, WhereCondition condition) {
		super(queryInfo);
		this.condition = condition;
	}

	@Override
	public AbstractState transitionToNextState(String token) throws ChiFariException {
		if (Keywords.WHERE_OPERATORS.contains(token) || token.equalsIgnoreCase(Keywords.IS_KEYWORD)) {
			if (token.equalsIgnoreCase(Keywords.IS_NOT_KEYWORDS[0])) {
				condition.setOperator("IS NOT");
				return new GreedyMatchKeywordState(queryInfo, Keywords.IS_NOT_KEYWORDS, q -> new WhereValueState(q, condition));
			}
			if (token.equalsIgnoreCase(Keywords.IS_KEYWORD)) {
				condition.setOperator("IS");
			} else {
				condition.setOperator(token);
			}
			return new WhereValueState(queryInfo, condition);
		}
		throw new ChiFariException(Keywords.WHERE_OPERATORS, token);
	}

}