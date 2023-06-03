package sc.alwe.trinacriasql.states.where;

import sc.alwe.trinacriasql.Keywords;
import sc.alwe.trinacriasql.model.ChiFariException;
import sc.alwe.trinacriasql.model.QueryInfo;
import sc.alwe.trinacriasql.model.WhereCondition;
import sc.alwe.trinacriasql.states.AbstractState;

/**
 * State for completing the last WHERE subclause in the format "field operator
 * value" by inserting the value.
 * 
 * @author Daniele Nicosia
 *
 */
public class WhereValueState extends AbstractState {

	private WhereCondition condition;

	public WhereValueState(QueryInfo queryInfo, WhereCondition condition) {
		super(queryInfo);
		this.condition = condition;
	}

	@Override
	public AbstractState transitionToNextState(String token) throws ChiFariException {
		if (token.equalsIgnoreCase(Keywords.NULL_KEYWORD)) {
			condition.setValue("NULL");
		} else {
			condition.setValue(token);
		}
		queryInfo.addWhereCondition(condition);
		return new WhereJoinState(queryInfo);
	}
	
}