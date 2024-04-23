package artemtsmyg.ru.DZ_sem4.repository;

import artemtsmyg.ru.DZ_sem4.model.Product;
import lombok.NonNull;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;


@Service
@Primary

/**
 * Реализация интерфейса Repository для работы с данными в памяти.
 * Класс содержит список продуктов и методы для работы с ними.
 * Все операции над списком продуктов синхронизированы с использованием ReentrantLock.
 */

public class RepositoryInMemory implements Repository {
    List<Product> productList;
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * Конструктор класса с передачей списка продуктов.
     * @param productList список продуктов
     */

    public RepositoryInMemory(List<Product> productList) {
        this.productList = productList;
    }


    @Override
    public List<Product> getAllProducts() {
        return productList;
    }


    @Override
    public Product getProductByArticle(int article) {
        if (productList.isEmpty()) return null;
        lock.lock();
        Product result = productList.stream()
                .filter(product -> product.getArticle() == article)
                .findFirst().orElse(null);
        lock.unlock();
        return result;
    }


    @Override
    public List<Product> findProduct(@NonNull String pattern) {
        if (productList.isEmpty()) return new ArrayList<>();
        lock.lock();
        List<Product> result = productList.stream()
                .filter(product -> {
                    try {
                        if (product.getTitle().contains(pattern) ||
                                product.getDescription().contains(pattern)) return true;
                        return product.getArticle() == Integer.parseInt(pattern) ||
                                product.getCost() == Double.parseDouble(pattern);
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }).collect(Collectors.toList());
        lock.unlock();
        return result;
    }


    @Override
    public synchronized void saveProduct(@NonNull Product product) {
        lock.lock();
        productList.add(product);
        lock.unlock();
    }


    @Override
    public synchronized void delProductByArticle(int article) {
        if (productList.isEmpty()) return;
        lock.lock();
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product pr = iterator.next();
            if (pr.getArticle() == article) {
                iterator.remove();
                break;
            }
        }
        lock.unlock();
    }
}