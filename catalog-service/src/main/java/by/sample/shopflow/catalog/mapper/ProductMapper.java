package by.sample.shopflow.catalog.mapper;

import by.sample.shopflow.catalog.model.Product;
import by.sample.shopflow.catalog.web.dto.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ProductMapper {

    @Mapping(target = "categoryId", source = "category.id")
    ProductResponse convert(Product product);
}
