package sc.alwe.trinacriasql;

import java.util.Arrays;
import java.util.List;

/**
 * Keywords used by TrinacriaSQL.
 * 
 * @author Daniele Nicosia
 *
 */
public class Keywords {

	public static final String SELECT_KEYWORD = "pigghiamu";
	public static final String UPDATE_KEYWORD = "refacìemu";
	public static final String[] INSERT_KEYWORDS = { "mìettemu", "rintra" };
	public static final String[] DELETE_KEYWORDS = { "livamu", "tuttu" };
	public static final String[] JOIN_KEYWORDS = { "iuncemo", "paro", "paro" };
	public static final String[] FROM_KEYWORDS = { "veni", "da" };
	public static final String[] ASTERISK_KEYWORDS = { "tuttu", "chiddu", "chi", "cc'è" };
	public static final String WHERE_KEYWORD = "unni";
	public static final String[] BEGIN_TRANSACTION_KEYWORDS = { "we", "compà" };
	public static final String[] COMMIT_KEYWORDS = { "finemula", "ccà" };
	public static final String[] ROLLBACK_KEYWORD = { "turnamu", "nnarrè" };
	public static final String AND_KEYWORD = "e";
	public static final String OR_KEYWORD = "o";
	public static final String NULL_KEYWORD = "nuddu";
	public static final String IS_KEYWORD = "è";
	public static final String VALUES_KEYWORD = "chisti";
	public static final String[] IS_NOT_KEYWORDS = { "nun", "è" };
	public static final String SET_KEYWORD = "mèttiri";
	public static final List<String> WHERE_OPERATORS = Arrays.asList(">", "<", "=", "!=", "<>", ">=", "<=",
		Keywords.IS_KEYWORD, Keywords.IS_NOT_KEYWORDS[0]);
	public static final String SET_EQUAL_KEYWORD = "accussì";
	public static final String LIMIT_KEYWORD = "firmati a";
	public static final String AS_KEYWORD = "comu";
}