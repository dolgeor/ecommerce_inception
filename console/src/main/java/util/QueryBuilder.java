package util;

import java.util.StringJoiner;

public class QueryBuilder {

    private final int EMPTY = 0;
    private final String ALL = "*";
    private final String QUESTION = "?";
    private final String END_OF_QUERY = ";";
    private final String SELECT = "SELECT %s FROM %s ";
    private final String INSERT = "INSERT INTO %s%sVALUES%s";
    private final String UPDATE = "UPDATE %s SET %s ";
    private final String DELETE = "DELETE FROM %s ";
    private final String WHERE = "WHERE%s ";

    private StringBuilder stringBuilder;

    public QueryBuilder() {
        stringBuilder = new StringBuilder();
    }

    public QueryBuilder select(String tableName, String... columns) {
        if (isValid(tableName)) {
            StringJoiner columnsList = createColumnsList(getEnumerationSimpleFormat(), columns).setEmptyValue(ALL);
            stringBuilder.append(String.format(SELECT, columnsList, tableName));
        }
        return this;
    }

    public QueryBuilder insert(String tableName, String... columns) {
        if (isValid(tableName, columns)) {
            StringJoiner columnsList = createColumnsList(getEnumerationWithBracketsFormat(), columns);
            StringJoiner valuesList = createValuesList(getEnumerationWithBracketsFormat(), columns);
            stringBuilder.append(String.format(INSERT, tableName, columnsList, valuesList));
        }
        return this;
    }

    public QueryBuilder update(String tableName, String... columns) {
        if (isValid(tableName, columns)) {
            StringJoiner columnsList = createColumnsList(getEnumerationWithQuestionsFormat(), columns);
            stringBuilder.append(String.format(UPDATE, tableName, columnsList));
        }
        return this;
    }

    public QueryBuilder delete(String tableName) {
        if (isValid(tableName)) {
            stringBuilder.append(String.format(DELETE, tableName));
        }
        return this;
    }

    public QueryBuilder where(String condition) {
        if (isValid(condition)) {
            StringJoiner columnsList = createColumnsList(getEnumerationWithQuestionsFormat(), condition);
            stringBuilder.append(String.format(WHERE, columnsList));
        }
        return this;
    }

    public QueryBuilder whereLike(String condition) {
        if (isValid(condition)) {
            StringJoiner columnsList = createColumnsList(getEnumerationWithLikeQuestionsFormat(), condition);
            stringBuilder.append(String.format(WHERE, columnsList));
        }
        return this;
    }

    public String build() {
        String sql = stringBuilder.toString().trim().replaceAll(" +", " ") + END_OF_QUERY;
        clear();
        return sql;
    }

    private void clear() {
        stringBuilder.setLength(EMPTY);
    }

    private StringJoiner getEnumerationSimpleFormat() {
        return new StringJoiner(", ", " ", " ");
    }

    private StringJoiner getEnumerationWithBracketsFormat() {
        return new StringJoiner(", ", " (", ") ");
    }

    private StringJoiner getEnumerationWithQuestionsFormat() {
        return new StringJoiner(" = ?, ", " ", " = ?");
    }

    private StringJoiner getEnumerationWithLikeQuestionsFormat() {
        return new StringJoiner(" LIKE ?, ", " ", " LIKE ?");
    }

    private StringJoiner createColumnsList(StringJoiner columnsList, String... columns) {
        for (String column : columns) {
            if (isValid(column)) {
                columnsList.add(column);
            }
        }
        return columnsList;
    }

    private StringJoiner createValuesList(StringJoiner columnsList, String... columns) {
        for (String column : columns) {
            if (isValid(column)) {
                columnsList.add(QUESTION);
            }
        }
        return columnsList;
    }

    private boolean isValid(String target) {
        if(target == null)
            return false;
        return !target.isEmpty();
    }

    private boolean isValid(String target, String... columns) {
        return !isEmpty(columns) && isValid(target);
    }

    private boolean isEmpty(String... columns) {
        return columns.length == EMPTY;
    }
}
