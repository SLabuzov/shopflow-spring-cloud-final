package by.sample.shopflow.catalog.repository;

import by.sample.shopflow.catalog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
