package by.sample.shopflow.order.mapper;

import by.sample.shopflow.order.model.Order;
import by.sample.shopflow.order.web.dto.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        uses = {OrderItemMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface OrderMapper {

    OrderResponse convert(Order order);
}
