package web.Webperfume.repository;


import org.springframework.stereotype.Repository;
import web.Webperfume.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
