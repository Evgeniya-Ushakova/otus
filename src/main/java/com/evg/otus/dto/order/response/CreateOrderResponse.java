package com.evg.otus.dto.order.response;

import com.evg.otus.dto.BaseResponse;
import com.evg.otus.dto.order.ProductDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CreateOrderResponse extends BaseResponse {

    private String orderKey;
    private Long orderId;
    private List<ProductDto> products;

}
