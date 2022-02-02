package mk.ukim.finki.wp.eshop.service.impl;

import mk.ukim.finki.wp.eshop.model.Category;
import mk.ukim.finki.wp.eshop.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.wp.eshop.model.Manufacturer;
import mk.ukim.finki.wp.eshop.model.Product;
import mk.ukim.finki.wp.eshop.model.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.wp.eshop.repository.impl.InMemoryCategoryRepository;
import mk.ukim.finki.wp.eshop.repository.impl.InMemoryManufacturerRepository;
import mk.ukim.finki.wp.eshop.repository.impl.InMemoryProductRepository;
import mk.ukim.finki.wp.eshop.repository.jpa.CategoryRepository;
import mk.ukim.finki.wp.eshop.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.wp.eshop.repository.jpa.ProductRepository;
import mk.ukim.finki.wp.eshop.repository.jpa.ShoppingCartRepository;
import mk.ukim.finki.wp.eshop.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final CategoryRepository categoryRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public ProductServiceImpl(ProductRepository productRepository, ManufacturerRepository manufacturerRepository, CategoryRepository categoryRepository, ShoppingCartRepository shoppingCartRepository) {
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.categoryRepository = categoryRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        this.productRepository.deleteByName(name);
        return Optional.of(this.productRepository.save(new Product(name, price, quantity, category, manufacturer)));
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}
