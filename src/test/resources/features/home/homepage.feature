@feature_homepage
Feature: Home Page Feature

  @smoke @verify_search_bar_default_value
  Scenario: Verify default value of search bar on homepage
    Then verify default value of search bar on homepage

  @smoke @verify_all_footer_text
  Scenario: Verify all footer text on homepage
    Then verify all footer text on homepage

  @verify_agreement_popup @excluded
  Scenario: Verify agreement popup on homepage
    Then verify agreement popup on homepage