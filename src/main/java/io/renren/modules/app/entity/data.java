package io.renren.modules.app.entity;

import lombok.Data;

import java.util.List;

@Data
public class data {
    private type type;
    private List<ProductEntity> product;

    public data(io.renren.modules.app.entity.type type, List<ProductEntity> product) {
        this.type = type;
        this.product = product;
    }
}
