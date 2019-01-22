package br.com.solutis.desafio.helper.filter;

import br.com.solutis.desafio.helper.NumberHelper;
import br.com.solutis.desafio.helper.StringHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.text.TextStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class FilterData {
    private int count;
    private int page;
    private int limit;
    private int totalPages;
    private String order;
    private List<WhereClause> whereClauses = new ArrayList<>();
    @JsonIgnore
    private String queryBase;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotalPages() {

        int result = 0;
        if (this.limit != 0) {
            result = this.count / this.limit;
            if ((this.count % this.limit) > 0) {
                result++;
            }
        }
        return result;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public List<WhereClause> getWhereClauses() {
        return whereClauses;
    }

    public void setWhereClauses(List<WhereClause> whereClauses) {
        this.whereClauses = whereClauses;
    }

    public String getQueryBase() {
        return queryBase;
    }

    public void setQueryBase(String queryBase) {
        this.queryBase = queryBase;
    }

    public String getQuery(boolean applyLimit) {
        TextStringBuilder query = new TextStringBuilder();
        query.appendln(this.getQueryBase());
        query.appendln(this.SqlWhere());
        if (applyLimit) {
            query.appendln(this.sqlLimit());
        }
        return query.toString();
    }

    private String SqlWhere() {
        TextStringBuilder query = new TextStringBuilder();

        for (WhereClause whereClause : getWhereClauses()) {
            if (StringHelper.isNotEmpty(whereClause.getIniValue().toString())) {
                query.append(whereClause.getWhereType());

                switch (whereClause.getMatchType()) {
                    case LIKE:
                        query.appendln(likeClause(whereClause));
                        break;
                    case NOT_LIKE:
                        query.appendln(likeClause(whereClause));
                        break;
                    case LIKE_START:
                        query.appendln(likeStartClause(whereClause));
                        break;
                    case LIKE_END:
                        query.appendln(likeEndClause(whereClause));
                        break;
                    case NOT_EQUAL:
                        query.appendln(defaultClause(whereClause));
                        break;
                    case GREATER:
                        query.appendln(defaultClause(whereClause));
                        break;
                    case GREATER_EQUAL:
                        query.appendln(defaultClause(whereClause));
                        break;
                    case LESS:
                        query.appendln(defaultClause(whereClause));
                        break;
                    case LESS_EQUAL:
                        query.appendln(defaultClause(whereClause));
                        break;
                    case BETWEEN:
                        query.appendln(betweenClause(whereClause));
                        break;
                    default:
                        EQUALS:
                        query.appendln(defaultClause(whereClause));
                        break;
                }
            }
        }
        return query.toString();
    }

    private String defaultClause(WhereClause whereClause) {
        String what = String.format("'%s'", whereClause.getIniValue().toString());
        if(whereClause.getDataType().equals(DataType.NUMBER)){
            what = what.replace("'","");
        }
        return String.format(" ( %s  %s %s ) ", buildWho(whereClause), whereClause.getMatchType().getSignal(), what);
    }

    private String betweenClause(WhereClause whereClause) {
        String what = String.format("'%s' and '%s'", whereClause.getIniValue().toString(),whereClause.getEndValue().toString());
        if(whereClause.getDataType().equals(DataType.NUMBER)){
            what = String.format("%s and %s", whereClause.getIniValue().toString(),whereClause.getEndValue().toString());
        }
        return String.format(" ( %s  %s %s ) ", buildWho(whereClause), whereClause.getMatchType().getSignal(), what);
    }

    private String likeClause(WhereClause whereClause) {
        String what = String.format("%%%s%%", whereClause.getIniValue().toString());
        return String.format(" ( %s  %s '%s' ) ", buildWho(whereClause), whereClause.getMatchType().getSignal(), what);
    }

    private String likeStartClause(WhereClause whereClause) {
        String what = String.format("%s%%", whereClause.getIniValue().toString());
        return String.format(" ( %s  %s '%s' ) ", buildWho(whereClause), whereClause.getMatchType().getSignal(), what);
    }

    private String likeEndClause(WhereClause whereClause) {
        String what = String.format("%%%s", whereClause.getIniValue().toString());
        return String.format(" ( %s  %s '%s' ) ", buildWho(whereClause), whereClause.getMatchType().getSignal(), what);
    }

    private String buildWho(WhereClause whereClause) {
        String who = String.format("%s.%s ", whereClause.getTable(), whereClause.getName());
        who = (whereClause.getDataType() == DataType.STRING) ? String.format(" %s ", who) : String.format(" %s::TEXT ", who);
        if(whereClause.getDataType().equals(DataType.NUMBER)){
            who =String.format(" %s::numeric ", who);
        }
        return who;
    }

    private String sqlLimit() {
        if (this.limit != 0) {
            int offSet = NumberHelper.major((this.page - 1) * this.limit, 0);
            return String.format(" offset %d limit %d ", offSet, this.limit);
        }
        return "";
    }

    public FilterData addWhereClause(WhereClause whereClause) {
        getWhereClauses().add(whereClause);
        return this;
    }
}

