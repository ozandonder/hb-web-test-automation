@feature_pdp
Feature: Product Detail Page Feature

  @verify_review_helpful_feedback_message
  Scenario: Verify review helpful feedback message on pdp page
    When search "iphone" on homepage
    And sort product list "review_count" type on product list page
    And click first product card and go to product detail on product list page
    And switch "last" tab
    And select review tab on product detail page
    When click first review thumbs up button on product detail page
    Then verify thank you message after review useful feedback on product detail page