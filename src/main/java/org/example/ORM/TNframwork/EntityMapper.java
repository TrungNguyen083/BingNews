package org.example.ORM.TNframwork;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityMapper {
    public static <T> T mapResultSetToObject(ResultSet resultSet, Class<T> clazz) throws SQLException {
        T object = null;
        try {
            object = clazz.getDeclaredConstructor().newInstance();

            // Assuming that the ResultSet column names match the entity field names
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                String columnName = resultSet.getMetaData().getColumnName(i);
                // Use reflection to set the field value in the object
                java.lang.reflect.Field field = clazz.getDeclaredField(columnName);
                field.setAccessible(true); // Allow accessing private fields
                field.set(object, resultSet.getObject(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}

