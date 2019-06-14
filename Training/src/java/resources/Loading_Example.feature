@nagaraju
Feature: To test my cucumber test is running with scenario outline
  
  
  Scenario Outline: Data table example
    Given Navigate to application 
    |URL|<URL>|
    When Search for
    |Keyword|<Keyword>|
    
    Examples:
    |URL|Keyword|
    |https://ww.bing.com|cucumber|
