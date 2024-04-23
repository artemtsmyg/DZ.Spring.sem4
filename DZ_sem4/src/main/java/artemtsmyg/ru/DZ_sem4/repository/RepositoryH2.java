package artemtsmyg.ru.DZ_sem4.repository;

import artemtsmyg.ru.DZ_sem4.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Реализация интерфейса Repository для работы с базой данных H2.
 */

public class RepositoryH2 implements Repository {
    JdbcTemplate jdbc;

    /**
     * Конструктор класса RepositoryH2.
     * @param jdbc объект JdbcTemplate для работы с базой данных
     */

    public RepositoryH2(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Метод для получения списка всех продуктов из базы данных.
     * @return список продуктов
     */

    @Override
    public List<Product> getAllProducts() {
        String sql = "SELECT * From products";
        return jdbc.query(sql, (resultSet, i) -> {
            Product product = new Product();
            product.setArticle(resultSet.getInt("article"));
            product.setCost(resultSet.getDouble("cost"));
            product.setTitle(resultSet.getString("title"));
            product.setDescription(resultSet.getString("description"));
            return product;
        });
    }

    /**
     * Метод для получения продукта по его артикулу.
     * @param article артикул продукта
     * @return продукт
     */

    @Override
    public Product getProductByArticle(int article) {
        String sql = "select * from products where article = ?";
        return jdbc.queryForObject(sql, Product.class, article);
    }

    /**
     * Метод для поиска продуктов по заданному шаблону.
     * @param pattern шаблон для поиска
     * @return список найденных продуктов
     */

    @Override
    public List<Product> findProduct(String pattern) {
        return null;
    }

    /**
     * Метод для сохранения нового продукта в базе данных.
     * @param product новый продукт для сохранения
     */

    @Override
    public void saveProduct(Product product) {
        String sql = "Insert into products ()";
    }

    /**
     * Метод для удаления продукта по его артикулу.
     * @param article артикул продукта для удаления
     */

    @Override
    public void delProductByArticle(int article) {
    }
}