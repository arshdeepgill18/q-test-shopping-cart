package com.qantastest.shoppingrus.discountrules;

import com.qantastest.shoppingrus.product.ProductItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PricingRule {

    BigDecimal apply(Map<String, List<ProductItem>> products, BigDecimal total);
}
