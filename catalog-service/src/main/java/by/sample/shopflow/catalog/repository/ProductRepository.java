package by.sample.shopflow.catalog.repository;

import by.sample.shopflow.catalog.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("""
            SELECT p FROM Product p JOIN FETCH p.category
            WHERE (:categoryId IS NULL OR p.category.id = :categoryId)
              AND (:available IS NULL OR p.available = :available)
            """)
    Page<Product> findFiltered(@Param("categoryId") UUID categoryId,
                               @Param("available") Boolean available,
                               Pageable pageable);

    List<Product> findAllByIdIn(List<UUID> ids);
}
