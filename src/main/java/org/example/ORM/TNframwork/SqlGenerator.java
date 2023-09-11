package org.example.ORM.TNframwork;

public class SqlGenerator {

    public static String generateSelectAllSql(String tableName, String[] columnNames) {
        StringBuilder sql = new StringBuilder("SELECT ");

        // Append column names
        for (int i = 0; i < columnNames.length; i++) {
            sql.append(columnNames[i]);
            if (i < columnNames.length - 1) {
                sql.append(", ");
            }
        }

        sql.append(" FROM ").append(tableName);

        return sql.toString();
    }

    public static String generateInsertSql(String tableName, String[] columnNames) {
        StringBuilder sql = new StringBuilder("INSERT INTO ");
        sql.append(tableName).append(" (");

        // Append column names
        for (int i = 0; i < columnNames.length; i++) {
            sql.append(columnNames[i]);
            if (i < columnNames.length - 1) {
                sql.append(", ");
            }
        }

        sql.append(") VALUES (");

        // Append placeholders for values
        for (int i = 0; i < columnNames.length; i++) {
            sql.append("?");
            if (i < columnNames.length - 1) {
                sql.append(", ");
            }
        }

        sql.append(")");

        return sql.toString();
    }

    public static String generateSelectByIdSql(String tableName, String[] columnNames, String guid) {
        StringBuilder sql = new StringBuilder("SELECT ");

        // Append column names
        for (int i = 0; i < columnNames.length; i++) {
            sql.append(columnNames[i]);
            if (i < columnNames.length - 1) {
                sql.append(", ");
            }
        }

        sql.append(" FROM ").append(tableName);
        sql.append(" WHERE ").append("guid").append(" = ").append(guid); // Assuming the first column is the ID

        return sql.toString();
    }

    public static String generateDeleteSql(String tableName, String primaryKeyValue) {
        StringBuilder sql = new StringBuilder("DELETE FROM ");
        sql.append(tableName);
        sql.append(" WHERE ");
        sql.append("guid");
        sql.append(" = ");
        sql.append("'" + primaryKeyValue + "'");
        return sql.toString();
    }

    public static String generateUpdateSql(String tableName, String[] columnNames, String guid) {
        StringBuilder sql = new StringBuilder("UPDATE ");
        sql.append(tableName);
        sql.append(" SET ");

        // Append column names to set new values
        for (int i = 0; i < columnNames.length; i++) {
            if(columnNames[i].equals("guid")) continue;
            sql.append(columnNames[i]);
            sql.append(" = ?");
            if (i < columnNames.length - 2) {
                sql.append(", ");
            }
        }

        // Add WHERE clause to specify the primary key for the update
        sql.append(" WHERE ");
        sql.append("guid");
        sql.append(" = ");
        sql.append("'" + guid + "'");

        return sql.toString();
    }


}
