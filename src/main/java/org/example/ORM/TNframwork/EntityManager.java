package org.example.ORM.TNframwork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.example.controller.BingNewsController.getPropertyValue;

public class EntityManager {
    private Connection connection;

    public <T> List<T> getAllItems(Class<T> entityClass) {
        connection = ConnectionManager.getConnection();
        EntityMetadata metadata = EntityMetadataExtractor.extract(entityClass);
        String tableName = metadata.getTableName();
        String[] columnNames = metadata.getColumnNames();
        String sql = SqlGenerator.generateSelectAllSql(tableName, columnNames);
        List<T> items = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T item = EntityMapper.mapResultSetToObject(resultSet, entityClass);
                items.add(item);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.closeConnection();
        }

        return items;
    }

    public void insertItem(Object entity) {
        connection = ConnectionManager.getConnection();
        EntityMetadata metadata = EntityMetadataExtractor.extract(entity.getClass());
        String tableName = metadata.getTableName();
        String sql = SqlGenerator.generateInsertSql(tableName, metadata.getColumnNames());

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            int parameterIndex = 1; // Start with the first parameter index

            // Iterate over the column names and get corresponding property values from the entity
            for (String columnName : metadata.getColumnNames()) {
                Object propertyValue = getPropertyValue(entity, columnName); // Implement this method to get property values
                preparedStatement.setObject(parameterIndex, propertyValue);
                parameterIndex++;
            }

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.closeConnection();
        }
    }

    public <T> T findById(Class<T> entityClass, String guid) {
        connection = ConnectionManager.getConnection();
        EntityMetadata metadata = EntityMetadataExtractor.extract(entityClass);
        String tableName = metadata.getTableName();
        String sql = SqlGenerator.generateSelectByIdSql(tableName, metadata.getColumnNames(), guid);
        T item = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                item = EntityMapper.mapResultSetToObject(resultSet, entityClass);
            }
            return item;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.closeConnection();
        }
    }

    public void deleteItem(Class<?> entityClass, String guid) {
        connection = ConnectionManager.getConnection();
        EntityMetadata metadata = EntityMetadataExtractor.extract(entityClass);
        String tableName = metadata.getTableName();
        String sql = SqlGenerator.generateDeleteSql(tableName, guid);

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.closeConnection();
        }
    }

    public void updateItem(Object entity, String guid) {
        connection = ConnectionManager.getConnection();
        EntityMetadata metadata = EntityMetadataExtractor.extract(entity.getClass());
        String tableName = metadata.getTableName();
        String sql = SqlGenerator.generateUpdateSql(tableName, metadata.getColumnNames(), guid);

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            int parameterIndex = 1;

            for (String columnName : metadata.getColumnNames()) {
                if(columnName.equals("guid")) continue;
                Object propertyValue = getPropertyValue(entity, columnName);
                preparedStatement.setObject(parameterIndex, propertyValue);
                parameterIndex++;
            }

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.closeConnection();
        }
    }
}
