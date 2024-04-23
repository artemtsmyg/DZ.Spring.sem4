package artemtsmyg.ru.DZ_sem4.services;

import artemtsmyg.ru.DZ_sem4.model.Product;
import artemtsmyg.ru.DZ_sem4.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Сервис для работы с продуктами.
 */
@Service
public class ProductService {
    @Autowired
    Repository productsRepository;

    /**
     * Получить все продукты.
     * @return список всех продуктов
     */

    public List<Product> getAllProducts() {
        return productsRepository.getAllProducts();
    }

    /**
     * Получить продукт по артикулу.
     * @param article артикул продукта
     * @return продукт с указанным артикулом
     */

    public Product getProductByArticle(int article) {
        return productsRepository.getProductByArticle(article);
    }

    /**
     * Найти продукты по шаблону.
     * @param pattern шаблон для поиска продуктов
     * @return список найденных продуктов
     */

    public List<Product> findProduct(String pattern) {
        return productsRepository.findProduct(pattern);
    }

    /**
     * Сохранить продукт.
     * @param product продукт для сохранения
     */

    public void saveProduct(Product product) {
        productsRepository.saveProduct(product);
    }

    /**
     * Удалить продукт по артикулу.
     * @param article артикул продукта для удаления
     */

    public void delProductByArticle(int article) {
        productsRepository.delProductByArticle(article);
    }
}