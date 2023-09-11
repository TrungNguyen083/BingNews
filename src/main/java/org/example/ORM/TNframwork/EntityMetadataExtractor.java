package org.example.ORM.TNframwork;


import java.lang.reflect.Field;

public class EntityMetadataExtractor {
    public static EntityMetadata extract(Class<?> entityClass) {
        EntityMetadata metadata = new EntityMetadata();

        // Get the entity table name from the annotation
        Entity entityAnnotation = entityClass.getAnnotation(Entity.class);
        if (entityAnnotation != null) {
            metadata.setTableName(entityAnnotation.name());
        } else {
            // Use the class name as the default table name
            metadata.setTableName(entityClass.getSimpleName());
        }

        // Extract field information (column names)
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column columnAnnotation = field.getAnnotation(Column.class);
                String columnName = columnAnnotation.name();
                if (columnName.isEmpty()) {
                    // If no column name is specified, use the field name
                    columnName = field.getName();
                }
                metadata.addColumnMetadata(field.getName(), columnName);
            }
        }

        return metadata;
    }
}
