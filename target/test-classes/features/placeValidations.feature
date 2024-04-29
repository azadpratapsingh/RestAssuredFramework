
Feature: Validating Place API


  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given Add place payload "<name>" "<language>" "<address>"
    When USer calls "AddPlaceAPI" with Post http request
    Then The API call got success eith status code 200
    Then "status" is response body is "OK"
    Then "scope" is response body is "APP"
    
   Example:
   	| name    | language | address            |
   	| AAhouse | English  | World Class Center |
    
    #
    #When USer calls DeleteAPI with Post http request
    #
    #
#
  #@tag2
  #Scenario Outline: Title of your scenario outline
    #Given I want to write a step with <name>
    #When I check for the <value> in step
    #Then I verify the <status> in step
#
    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |
