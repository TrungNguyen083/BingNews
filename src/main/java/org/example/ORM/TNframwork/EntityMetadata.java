package org.example.ORM.TNframwork;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class EntityMetadata {
    private String tableName;
    private Map<String, String> columnMetadata;

    public EntityMetadata() {
        this.columnMetadata = new HashMap<>();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, String> getColumnMetadata() {
        return columnMetadata;
    }

    public void addColumnMetadata(String fieldName, String columnName) {
        columnMetadata.put(fieldName, columnName);
    }

    public String getColumnName(String fieldName) {
        return columnMetadata.get(fieldName);
    }

    public String[] getColumnNames() {
        return columnMetadata.values().toArray(new String[0]);
    }
}
