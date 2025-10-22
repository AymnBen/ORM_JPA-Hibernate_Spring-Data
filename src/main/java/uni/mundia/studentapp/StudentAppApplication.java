package uni.mundia.studentapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uni.mundia.studentapp.entities.Product;
import uni.mundia.studentapp.repository.ProductRepository;

import java.util.List;

@SpringBootApplication
public class StudentAppApplication implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;
    public static void main(String[] args) {
        SpringApplication.run(StudentAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        productRepository.save(new Product(null,"Computer",4000,4));
        //productRepository.save(new Product(null,"Printer",1200,7));
        //productRepository.save(new Product(null,"Phone",3200,40));
        List<Product> products = productRepository.findAll();
        products.forEach(p->{
            System.out.println(p.toString());
        });
        Product product = productRepository.findById(Long.valueOf(1)).get();
        //System.out.println(product.getId());
        //System.out.println(product.getName());
        //System.out.println(product.getPrice());
        //System.out.println(product.getQuantity());
        System.out.println("***********");

        List<Product> productList = productRepository.findByNameContains("C");
        productList.forEach(p->{
            System.out.println(p);
        });
        System.out.println("------------------");
        List<Product> productList2 = productRepository.search("%C%");
        productList2.forEach(p->{
            System.out.println(p);
        });

        System.out.println("------------------");
        List<Product> productList3 = productRepository.searchByPrice(3000.00);
        productList3.forEach(p->{
            System.out.println(p);
        });

    }

}
