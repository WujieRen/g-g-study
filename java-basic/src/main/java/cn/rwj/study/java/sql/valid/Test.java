package cn.rwj.study.java.sql.valid;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLWithSubqueryClause;
import com.alibaba.druid.sql.dialect.hive.parser.HiveStatementParser;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;

import java.util.Collection;
import java.util.List;

/**
 * @author rwj
 * @since 2024/8/12
 */
public class Test {

    public static void main(String[] args) {
//        String sql = "--查询语句\n WITH tmp as (SELECT f1,f2 FROM test.t1 WHERE f2=';;;') SELECT * FROM tmp; \n--删除语句\n  DELETE FROM test.t2 WHERE 1=1; TRUNCATE TABLE test.f3";
//        String sql = "SELECT * FROM test.t1";
        String sql = "--查询语句\n WITH tmp as (SELECT f1,f2 FROM test.t1 WHERE f2=';;;'), t2 AS (SELECT f3,f4 FROM test.t1 WHERE f2=';;;') SELECT * FROM tmp; \n--删除语句\n  DELETE FROM test.t2 WHERE 1=1; TRUNCATE TABLE test.f3";
        HiveStatementParser parser = new HiveStatementParser(sql);
        List<SQLStatement> sqlStatements = parser.parseStatementList();
        System.out.println(sqlStatements.size());

        SQLStatement sqlStatement = sqlStatements.get(0);
        SchemaStatVisitor visitor = new SchemaStatVisitor(JdbcConstants.HIVE);
        Collection<TableStat.Column> columns = visitor.getColumns();
        sqlStatement.accept(visitor);
        System.out.println("--------name\tfullname\tdatatype-------------------");
        for (TableStat.Column column : columns) {
            System.out.println(column.getName() + "\t" + column.getFullName() + "\t" + column.getDataType());
        }
        System.out.println("-------------  测试 SELECT *  语法 ----------------");
        System.out.println();
        System.out.println();

        String sqlString = SQLUtils.toSQLString(sqlStatements.get(0), JdbcConstants.HIVE, null, null);
        System.out.println(sqlString);

        boolean a = sqlStatements.get(0) instanceof SQLSelectStatement;
        System.out.println(a);

        boolean b = sqlStatements.get(1) instanceof SQLSelectStatement;
        System.out.println(b);

        boolean c = sqlStatements.get(2) instanceof SQLSelectStatement;
        System.out.println(c);

    }

    private static void validSql() {

    }

}
