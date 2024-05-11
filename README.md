# HP Web Test Automation

## Tools

* **Java17** - Lang
* **Intellij IDE** - IDE
* **Selenium** - Web APP Test Automation Tool
* **Cucumber7** - Gherkin Lang Framework
* **Junit5** - Assertion & Validation Framework
* **Allure** - Raporting
* **Logback** - Logging

## Test Run

1. It can be run based on scenario or feature by pressing the green RUN button on the IDE


2. Run with mvn command

> **Note:** allure must be setup

* Run with tags

  ```
  mvn clean test -Dtest="CucumberRunnerTest" -D"cucumber.filter.tags=@successfully_login" -DthreadCount=1 
  ```

* Run with groups

  ```
  mvn clean test -Dtest="CucumberRunnerTest" -D"cucumber.filter.tags=@smoke"
  ```

* System env

  ```
  mvn clean test -Denv="local"
  ```

## Allure

* Run command

`allure serve target/allure-results `

## Project Structure

```                   
├── src
│   ├── test                      
│   │   ├── java
│   │   │  ├── configs    
│   │   │  │   └── BaseConfig 
│   │   │  ├── pages  
│   │   │  │   └── home          
│   │   │  │      └── HomePage
│   │   │  │   └── BasePage            
│   │   │  ├── steps  
│   │   │  │   └── home
│   │   │  │      └── HomePageSteps
│   │   │  │   └── BaseSteps  
│   │   │  ├── utils
│   │   │  │   └── DriverHook              
│   │   │  │   ├── LoggingUtil              
│   │   │  │   ├── ResourceFileReader              
│   │   │  │   ├── WebDriverHelper              
│   │   │  └── CucumberRunnerTest      
│   │   ├── resources
│   │   │  ├── features  
│   │   │  │  └── home
│   │   │  │      └── homepage.feature 
│   │   │  ├── allure.properties  
│   │   │  ├── logback.xml  
├── pom.xml
└── README.md
```

## Naming Convention

```
package name = my_package

file name = MyFile

feature file name = my_feature_file.feature

class name = MyClass

method name = myMethodName

variable name = myVariable

enum = ALL_CAPITAL

tag name = @my_tag 
```

# Web Element Standartları

| Prefix | Örnek        | Locator   |
|--------|--------------|-----------|
| BTN    | BTN_LOGIN    | Button    |
| CHK    | CHK_STATUS   | Checkbox  |
| CBX    | CBX_ENGLISH  | Combo box |
| LBL    | LBL_USERNAME | Label     |
| DRP    | DRP_LIST     | Drop down |
| SLC    | SLC_LIST     | Selectbox |
| TXT    | TXT_EMAIL    | Textbox   |
| IMG    | IMG_LOGO     | Image     |
| RDX    | RDX_FEMALE   | Radiobox  |
| LIST   | LIST_PRODUCT | List      |

* Web Element descriptions should be listed alphabetically

Example:

```
 By BTN_SEND
 By BTN_SEARCH_BAR
 By LBL_ERROR_MESSAGE
 By TXT_SEARCH_BAR
```

# Page Method Standartları

| Prefix | Action        | Description                 |
|--------|---------------|-----------------------------|
| click  | clickRegister | Click button or link        |
| fill   | fillEmail     | Type textbox                |
| check  | checkGender   | Check a check box           |
| select | selectYear    | Select value from drop down |
| verify | verifyMenu    | Assertion                   |

* Method definitions should be listed alphabetically

Example:

```
  public void addValidAddress
    ###
  
  public void clickMyAddressPage
    ###
  
  public void goToMyAddressPage
    ###
  
  public void verifyAddedDeliveryAddress
     ###
```

# Scenario Writing Standards

* Scenarios will be written in the feature file
* Use ``Given, When, Then, And`` syntax
* After the ``Feature`` keyword, name the relevant feature
* Write the description of the feature on the next line
* Scenarios will be written after the ``Scenario`` keyword. Scenario name must be unique
* Each scenario should be tagged. Tags should be placed at the top of the scenario. For example, @regression, @smoke
* When writing scenario steps, follow the example scenario below

```
Given pre-condition
And additional conditions if any
When action is taken
And additional actions if any
Then verify relevant verifications
And additional verifications 
```

**Scenario Example**

```
Given homepage is opened
And click login button
And fill in the valid credentials
When click login button
Then verify my account icon
And title should change to "My Title"
```

# Step Definition Formula

* action + object + location

  `And click login button on homepage`


* action + object + value + location

  `And set email with "m@f.com" on homepage`


* verification action + object + location

  `Then verify the new address on my delivery addresses page`


* verification action + object + value + location

  `Then verify the new address title is "Home" on my delivery addresses page`


* Step definitions (features/step_definitions) should be sorted alphabetically

Example:
```
  And(/^add valid delivery address$/) do
    ###
  end
  
  And(/^click go to my address page$/) do
    ###
  end
  
  And(/^go to my delivery address page$/) do
    ###
  end
  
  Then(/^verify added delivery address$/) do
     ###
  end
```

# Resource Naming Convention

* contents.json : textual, graphical, or multimedia elements present on a web page
* resources.json : resources are external files or services that a web page uses to function properly
* validation.json : validations refer to the checks performed on input data, interactions, or outputs to ensure correctness and integrity

Example:

```
   {
     "tr": {
          "home_page":  "Giriş Yap"
      },
     "en": {
      }
   }
```

# Commit ve PR structure

```
* Branch isimleri işin ticket idsi ile açılmalı. Örnek: QA-74
* Mümkün olduğunca commitler anlaşılır açıklamalar ile commitlenmeli.
* Commit atılırken mümkün olduğunca küçük parçalar halinde ilerlenmeli(Atomic). Böylelikle rollback daha kolay olacaktır.
* PR QA ekibinden zorunlu review ırların review etmesinden sonra master a mergelenecektir.
* Master'a mergelenmeden önce pipeline ilgili branch üzerinde çalıştırılacak ve herhangi bir problem olmadığı doğrulanacaktır.
```

## Tagging

* When tagging scenarios snake_case should be used

```  
@smoke = A scenario/feature which is expected to run in the smoke scope
@prod  = Scenarios to be run on prod environment
@regression  = Cases to be run during regression
@feature_tag = Tag name to be given to each feature file. Example: @feature_login
@scenario_tag = The unique tag to be given to each scenario. Example: @success_login
@ignore  = Ignoring a subset of scenarios
@excluded  = Used for scenarios that no longer exist in the application   
```