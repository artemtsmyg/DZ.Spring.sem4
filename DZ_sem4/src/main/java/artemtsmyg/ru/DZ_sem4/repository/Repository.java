package artemtsmyg.ru.DZ_sem4.repository;

import artemtsmyg.ru.DZ_sem4.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Repository - интерфейс реализующий методы управления списком продуктов.
 */
@Service
public interface Repository {

    List<Product> getAllProducts(); // Получить список всех продуктов

    Product getProductByArticle(int article); // Получить продукт по артикулу

    List<Product> findProduct(String pattern); // Найти продукты по шаблону

    void saveProduct(Product product); // Сохранить продукт

    void delProductByArticle(int article); // Удалить продукт
}