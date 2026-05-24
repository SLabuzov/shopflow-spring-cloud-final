package by.sample.shopflow.order.mapper;

import by.sample.shopflow.order.model.OrderItem;
import by.sample.shopflow.order.web.dto.OrderItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface OrderItemMapper {

    @Mapping(target = "price", source = "priceSnapshot")
    OrderItemResponse convert(OrderItem orderItem);
}
