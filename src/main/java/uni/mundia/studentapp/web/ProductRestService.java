package uni.mundia.studentapp.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uni.mundia.studentapp.entities.Product;
import uni.mundia.studentapp.repository.ProductRepository;

import java.util.List;

@RestController
public class ProductRestService {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products/{id}")
    public Product findProducts(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return product ;
    }
}
