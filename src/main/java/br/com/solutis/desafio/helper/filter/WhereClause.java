package br.com.solutis.desafio.helper.filter;

public class WhereClause {
    private String table;
    private String label;
    private String name;
    private Object iniValue = "";
    private Object endValue = "";
    private MatchType matchType = MatchType.EQUALS;
    private DataType dataType = DataType.STRING;
    private WhereType whereType = WhereType.AND;

    public String getTable() {
        return table;
    }

    public WhereClause setTable(String table) {
        this.table = table;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public WhereClause setLabel(String label) {
        this.label = label;
        return this;
    }

    public String getName() {
        return name;
    }

    public WhereClause setName(String name) {
        this.name = name;
        return this;
    }

    public Object getIniValue() {
        return iniValue;
    }

    public WhereClause setIniValue(Object iniValue) {
        this.iniValue = iniValue;
        return this;
    }

    public Object getEndValue() {
        return endValue;
    }

    public WhereClause setEndValue(Object endValue) {
        this.endValue = endValue;
        return this;
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public WhereClause setMatchType(MatchType matchType) {
        this.matchType = matchType;
        return this;
    }

    public DataType getDataType() {
        return dataType;
    }

    public WhereClause setDataType(DataType dataType) {
        this.dataType = dataType;
        return this;
    }

    public WhereType getWhereType() {
        return whereType;
    }

    public WhereClause setWhereType(WhereType whereType) {
        this.whereType = whereType;
        return this;
    }
}
