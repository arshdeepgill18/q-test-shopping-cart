package com.qantastest.shoppingrus;

import com.qantastest.shoppingrus.discountrules.PricingRule;
import com.qantastest.shoppingrus.product.ProductItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Checkout {

    private List<PricingRule> pricingRules;
    private Map<String, List<ProductItem>> products;

    public Checkout(List<PricingRule> pricingRules) {
        super();
        this.pricingRules = pricingRules;
        this.products = new HashMap<>();
    }

    public void scan(ProductItem item) {
        if (products.containsKey(item.getSku())) {
            products.get(item.getSku()).add(item);
        } else {
            List<ProductItem> itemList = new ArrayList<>();
            itemList.add(item);
            products.put(item.getSku(), itemList);
        }
    }

    public BigDecimal total() {
        BigDecimal total = BigDecimal.ZERO;
        for (PricingRule rule : pricingRules) {
            total = rule.apply(products, total);
        }
        total = applyFullPriceWithNoDiscounts(products, total);
        return total;
    }

    private BigDecimal applyFullPriceWithNoDiscounts(Map<String, List<ProductItem>> products, BigDecimal total) {
        for (String key : products.keySet()) {
            total = this.products.get(key).stream().map(ProductItem::getPrice).reduce(total, BigDecimal::add);
        }
        return total;
    }
}
