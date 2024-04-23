package artemtsmyg.ru.DZ_sem4.controller;

import artemtsmyg.ru.DZ_sem4.model.Product;
import artemtsmyg.ru.DZ_sem4.services.ProductService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ArticlesController - класс обработки запросов на добавление и удаление продуктов, используемый редактором.
 */
@Controller
@Log
public class ProductController {
    Product product;
    @Autowired
    ProductService productService;

    /**
     * Метод для обработки запроса на получение главной страницы.
     * @param model модель для передачи данных в представление
     * @return имя представления для главной страницы
     */


    @GetMapping("/")
    public String indexHtml(Model model) {
        model.addAttribute(productService.getAllProducts());
        return "index";
    }

    /**
     * Метод для обработки POST запроса на добавление новой публикации.
     * @param product новая публикация для добавления
     * @param model модель для передачи данных в представление
     * @return имя представления для главной страницы
     */

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String addProduct(Product product, Model model) {
        System.out.println(product);
        productService.saveProduct(product);
        model.addAttribute(productService.getAllProducts());
        return "index";
    }

    /**
     * Метод для обработки запроса на удаление публикации по её идентификатору.
     * @param article идентификатор публикации для удаления
     * @param model модель для передачи данных в представление
     * @return имя представления для главной страницы
     */

    @GetMapping("/del/{article}")
    public String delProduct(@PathVariable int article, Model model) {
        System.out.println(article);
        productService.delProductByArticle(article);
        model.addAttribute(productService.getAllProducts());
        return "index";
    }
}
