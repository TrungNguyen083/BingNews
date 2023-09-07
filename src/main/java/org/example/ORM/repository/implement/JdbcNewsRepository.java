package org.example.ORM.repository.implement;

import org.example.ORM.repository.NewsRepository;
import org.example.ORM.util.ConnectionManager;
import org.example.model.News;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcNewsRepository implements NewsRepository {
    private static final String SELECT_ALL_NEWS = "SELECT * FROM News";
    private static final String INSERT_NEWS = "INSERT INTO News (guid, title, description, link, pubDate, image) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_GUID = "SELECT COUNT(*) FROM News WHERE guid = ?";

    @Override
    public List<News> getAllNews() {
        List<News> newsList = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_NEWS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                News news = new News();
                news.setGUID(resultSet.getString("guid"));
                news.setTitle(resultSet.getString("title"));
                news.setDescription(resultSet.getString("description"));
                news.setLink(resultSet.getString("link"));
                news.setPubDate(resultSet.getString("pubDate"));
                news.setImage(resultSet.getString("image"));
                newsList.add(news);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    @Override
    public News getNewsById(String news) {
        return null;
    }

    @Override
    public void insertNews(News news) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEWS)) {

            preparedStatement.setString(1, news.getGUID());
            preparedStatement.setString(2, news.getTitle());
            preparedStatement.setString(3, news.getDescription());
            preparedStatement.setString(4, news.getLink());
            preparedStatement.setString(5, news.getPubDate());
            preparedStatement.setString(6, news.getImage());

            if (!checkNewsExist(news.getGUID())) {
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateNews(String imgURL, String title, String sourceURL, String newsId) {

    }

    @Override
    public void deleteNews(String newsId) {

    }

    @Override
    public boolean checkNewsExist(String guid) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GUID)) {

            // Set the parameter for the prepared statement
            preparedStatement.setString(1, guid);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) > 0;
            }
        }
    }
}
