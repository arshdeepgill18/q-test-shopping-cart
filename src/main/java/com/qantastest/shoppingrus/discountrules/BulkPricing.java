package com.qantastest.shoppingrus.discountrules;

import com.qantastest.shoppingrus.product.ProductItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class BulkPricing implements PricingRule {
    private String sku;
    private BigDecimal discountedPrice;
    private int bulkQty;

    public BulkPricing(String sku, BigDecimal price, int bulkQty) {
        super();
        this.sku = sku;
        this.discountedPrice = price;
        this.bulkQty = bulkQty;
    }

    @Override
    public BigDecimal apply(Map<String, List<ProductItem>> products, BigDecimal total) {
        List<ProductItem> productItemList = products.get(sku);
        int numberOfItems = productItemList.size();

        if (numberOfItems > bulkQty) {
            total = total.add(discountedPrice.multiply(BigDecimal.valueOf(numberOfItems)));
        } else {
            BigDecimal orginialPrice = productItemList.get(0).getPrice();
            total = total.add(orginialPrice.multiply(BigDecimal.valueOf(numberOfItems)));
        }
        products.remove(sku);

        return total;
    }

}
