package via.sdj3.slaughterhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import via.sdj3.slaughterhouse.model.Animal;
import via.sdj3.slaughterhouse.protobuf.Product;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long>
{
}
