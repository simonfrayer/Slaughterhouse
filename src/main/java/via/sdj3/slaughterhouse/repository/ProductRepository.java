package via.sdj3.slaughterhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import via.sdj3.slaughterhouse.protobuf.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
