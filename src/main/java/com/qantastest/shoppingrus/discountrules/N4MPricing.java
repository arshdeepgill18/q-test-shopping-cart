package com.qantastest.shoppingrus.discountrules;

import com.qantastest.shoppingrus.product.ProductItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class N4MPricing implements PricingRule {

    private int n;
    private int m;
    private String sku;

    public N4MPricing(int n, int m, String sku) {
        super();
        this.n = n;
        this.m = m;
        this.sku = sku;
    }

    @Override
    public BigDecimal apply(Map<String, List<ProductItem>> products, BigDecimal total) {
        if (products.containsKey(sku)) {

            List<ProductItem> productItemList = products.get(sku);
            int numberOfItems = productItemList.size();
            int multipleOfN = numberOfItems / n;
            int remainderItems = numberOfItems % n;

            BigDecimal price = productItemList.get(0).getPrice();
            total = total.add(price.multiply(BigDecimal.valueOf(multipleOfN).multiply(BigDecimal.valueOf(m))))
                    .add(price.multiply(BigDecimal.valueOf(remainderItems)));
            products.remove(sku);
        }
        return total;
    }

}
