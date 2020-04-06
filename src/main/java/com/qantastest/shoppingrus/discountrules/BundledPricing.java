package com.qantastest.shoppingrus.discountrules;

import com.qantastest.shoppingrus.product.ProductItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class BundledPricing implements PricingRule {
    private String sku;
    private String freeSku;

    public BundledPricing(String sku, String freeSku) {
        super();
        this.sku = sku;
        this.freeSku = freeSku;
    }

    @Override
    public BigDecimal apply(Map<String, List<ProductItem>> products, BigDecimal total) {
        List<ProductItem> mainProductsInCart = products.get(sku);
        List<ProductItem> freeProductItemInCart = products.get(freeSku);
        int numberOfMainItems = mainProductsInCart.size();
        int numberOfFreeItems = freeProductItemInCart.size();

        int chargeableQty = numberOfFreeItems - numberOfMainItems;
        if (chargeableQty > 0) {
            products.put(freeSku, freeProductItemInCart.subList(0, chargeableQty - 1));
        } else {
            products.remove(freeSku);
        }

        BigDecimal orginialPrice = mainProductsInCart.get(0).getPrice();
        total = total.add(orginialPrice.multiply(BigDecimal.valueOf(numberOfMainItems)));
        products.remove(sku);
        return total;
    }

}
