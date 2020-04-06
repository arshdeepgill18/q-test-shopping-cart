# q-test-shopping-cart
Shopping cart's checkout system test exercise
----------------------------------------------
ShoppingRUs is starting a computer store. You have been engaged to build the checkout system. We
will start with the following products in our catalogue

| SKU     | Name        | Price    |
| --------:| ----------------:| ----------:|
| ipd     | Super iPad  | $549.99  |
| mbp     | MacBook Pro | $1399.99 |
| atv     | Apple TV    | $109.50   |
| vga     | VGA adapter | $30.00   |

As we&#39;re launching our new computer store, we would like to have a few opening day specials.

```
● We&#39;re going to have a 3 for 2 deal on Apple TVs. For example, if you buy 3 Apple TVs, you
will pay the price of 2 only

● The brand new Super iPad will have a bulk discounted applied, where the price will drop to
$499.99 each, if someone buys more than 4

● We will bundle in a free VGA adapter free of charge with every MacBook Pro sold
As our Sales manager is quite indecisive, we want the pricing rules to be as flexible as possible as
they can change in the future with little notice.
```
Our checkout system can scan items in any order.
The interface to our checkout looks like this (shown in java):
```java
  Checkout co = new Checkout(pricingRules);
  co.scan(item1);
  co.scan(item2);
  co.total();
```
Your task is to implement a checkout system that fulfils the requirements described above.

```
Example scenarios
-----------------
SKUs Scanned: atv, atv, atv, vga
Total expected: $249.00
SKUs Scanned: atv, ipd, ipd, atv, ipd, ipd, ipd
Total expected: $2718.95
SKUs Scanned: mbp, vga, ipd
Total expected: $1949.98
Notes on implementation:

● Use any language you wish
● Try not to spend more than 2 hours maximum. (We don&#39;t want you to lose a weekend over
this!)
● Don&#39;t build guis etc, we&#39;re more interested in your approach to solving the given task, not how
shiny it looks
● Don&#39;t worry about making a command line interface to the application
● Don&#39;t use any frameworks (rails, spring etc), or any external jars/gems (unless it&#39;s for testing)
```

# Proposed Solution
I have created a simple Java 8 app (src attached in the email) with junit tests for each
example scenario given above.
## Assumptions &amp; Notes:
````
● The pricing rules are not in any precedence order.
● SKU is being used as a product code rather than a unique stock keep unit for each
item.
● I have only added unit tests for the example scenarios only
