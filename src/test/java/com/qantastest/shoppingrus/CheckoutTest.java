package com.qantastest.shoppingrus;

import com.qantastest.shoppingrus.discountrules.BulkPricing;
import com.qantastest.shoppingrus.discountrules.PricingRule;
import com.qantastest.shoppingrus.discountrules.BundledPricing;
import com.qantastest.shoppingrus.discountrules.N4MPricing;
import com.qantastest.shoppingrus.product.ProductItem;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;

public class CheckoutTest {

    private Checkout checkout;

    @Test
    public void shouldReturnZeroWhenNoItemIsAdded() {
        List<PricingRule> pricingRules = new ArrayList<>();
        checkout = new Checkout(pricingRules);
        assertThat(checkout.total(), CoreMatchers.is(BigDecimal.ZERO));
    }

    @Test
    public void shouldReturnFullPriceWhenNoDiscountIsApplied() {
        List<PricingRule> pricingRules = new ArrayList<>();
        checkout = new Checkout(pricingRules);
        checkout.scan(new ProductItem("atv", "AppleTv", BigDecimal.valueOf(109.50)));
        checkout.scan(new ProductItem("atv", "AppleTv", BigDecimal.valueOf(109.50)));

        assertThat(checkout.total(), CoreMatchers.is(BigDecimal.valueOf(219.00)));
    }

    @Test
    public void shouldReturnDiscountedTotalWhen3For2DiscountIsAppliedForMultipleSkus() {
        List<PricingRule> pricingRules = new ArrayList<>();
        pricingRules.add(new N4MPricing(3, 2, "atv"));
        pricingRules.add(new N4MPricing(3, 2, "ipd"));
        checkout = new Checkout(pricingRules);

        checkout.scan(new ProductItem("atv", "AppleTv", BigDecimal.valueOf(109.50)));
        checkout.scan(new ProductItem("atv", "AppleTv", BigDecimal.valueOf(109.50)));
        checkout.scan(new ProductItem("atv", "AppleTv", BigDecimal.valueOf(109.50)));
        checkout.scan(new ProductItem("ipd", "Super iPad", BigDecimal.valueOf(549.99)));
        checkout.scan(new ProductItem("ipd", "Super iPad", BigDecimal.valueOf(549.99)));
        checkout.scan(new ProductItem("ipd", "Super iPad", BigDecimal.valueOf(549.99)));

        assertThat(checkout.total(), CoreMatchers.is(BigDecimal.valueOf(1318.98)));
    }

    @Test
    public void shouldReturnDiscountedTotalWhen3For2PricingIsApplied_Example1() {
        List<PricingRule> pricingRules = new ArrayList<>();
        pricingRules.add(new N4MPricing(3, 2, "atv"));
        checkout = new Checkout(pricingRules);

        checkout.scan(new ProductItem("atv", "AppleTv", BigDecimal.valueOf(109.50)));
        checkout.scan(new ProductItem("atv", "AppleTv", BigDecimal.valueOf(109.50)));
        checkout.scan(new ProductItem("atv", "AppleTv", BigDecimal.valueOf(109.50)));
        checkout.scan(new ProductItem("vga", "VGA Adapter", BigDecimal.valueOf(30.00)));

        assertThat(checkout.total(), CoreMatchers.is(BigDecimal.valueOf(249.00)));
    }

    @Test
    public void shouldReturnDiscountedTotalWhenMoreThan4PricingIsApplied_Example2() {
        List<PricingRule> pricingRules = new ArrayList<>();
        pricingRules.add(new BulkPricing("ipd", BigDecimal.valueOf(499.99), 4));
        checkout = new Checkout(pricingRules);

        checkout.scan(new ProductItem("atv", "AppleTv", BigDecimal.valueOf(109.50)));
        checkout.scan(new ProductItem("ipd", "Super iPad", BigDecimal.valueOf(549.99)));
        checkout.scan(new ProductItem("ipd", "Super iPad", BigDecimal.valueOf(549.99)));
        checkout.scan(new ProductItem("atv", "AppleTv", BigDecimal.valueOf(109.50)));
        checkout.scan(new ProductItem("ipd", "Super iPad", BigDecimal.valueOf(549.99)));
        checkout.scan(new ProductItem("ipd", "Super iPad", BigDecimal.valueOf(549.99)));
        checkout.scan(new ProductItem("ipd", "Super iPad", BigDecimal.valueOf(549.99)));

        assertThat(checkout.total(), CoreMatchers.is(BigDecimal.valueOf(2718.95)));
    }

    @Test
    public void shouldReturnDiscountedTotalWhenBundledPricingIsApplied_Example3() {
        List<PricingRule> pricingRules = new ArrayList<>();
        pricingRules.add(new BundledPricing("ipd", "vga"));

        checkout = new Checkout(pricingRules);

        checkout.scan(new ProductItem("mbp", "MacBook Pro", BigDecimal.valueOf(1399.99)));
        checkout.scan(new ProductItem("vga", "VGA adapter", BigDecimal.valueOf(30.00)));
        checkout.scan(new ProductItem("ipd", "Super iPad", BigDecimal.valueOf(549.99)));

        assertThat(checkout.total(), CoreMatchers.is(BigDecimal.valueOf(1949.98)));
    }
}
