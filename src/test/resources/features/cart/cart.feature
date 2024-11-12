@feature_cart
Feature: Cart Page Feature

  @verify_product_price
  Scenario: Verify product price on cart page
    When search "iphone" on homepage
    And sort product list "review_count" type on product list page
    And click random product card and go to product detail on product list page
    And switch "last" tab
    When set product detail on product detail page
    And add product to cart and go to cart on product detail page
    Then verify product price on cart page