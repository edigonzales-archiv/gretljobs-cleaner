package ch.so.agi.gretl.jobs.cleaner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.facebook.presto.sql.parser.ParsingOptions;
import com.facebook.presto.sql.parser.SqlParser;
import com.facebook.presto.sql.tree.Expression;
import com.facebook.presto.sql.tree.Query;
import com.facebook.presto.sql.tree.QuerySpecification;
import com.facebook.presto.sql.tree.Select;

public class PrestoParser {
	public List getTableList(String sql) {
        SqlParser parser = new SqlParser();
        //sql = "SELECT * FROM ta LEFT JOIN tb ON ta.a = tb.a";
        Query query = (Query)parser.createStatement(sql, new ParsingOptions());
        System.out.println(query);
        
        QuerySpecification body = (QuerySpecification)query.getQueryBody();
        System.out.println(body);
//        Select select = body.getSelect();
        //System.out.println("Columns = " + select.getSelectItems());
//        System.out.println("From = " + body.getFrom().get());
//        Optional<Expression> where = body.getWhere();
//        System.out.println("Where = " + where.get());
//        System.out.println("Group by = " + body.getGroupBy());
//        System.out.println("Order by = " + body.getOrderBy());
//        System.out.println("Limit = " + body.getLimit().get());

        
        return new ArrayList();

	}
}
