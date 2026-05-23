package by.sample.shopflow.catalog.mapper;

import by.sample.shopflow.catalog.model.Category;
import by.sample.shopflow.catalog.web.dto.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface CategoryMapper {

    CategoryResponse convert(Category category);
}
