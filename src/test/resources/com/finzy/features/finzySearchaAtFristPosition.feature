Feature: Test Finzy website ​appears

  Scenario Outline: Verify finzy website ​appears ​first ​in ​a ​google web ​search.
    When user search "<SearchKeyword>" as a google web  search
    Then finzy website should appears at <page> and <position>
    Examples:
      | SearchKeyword               | page | position |
      | low-risk investments Finzy  | 1    | 1        |
      | BORROW from Finzy.com       | 1    | 1        |
      | low-NBFC-P2P  Finzy         | 1    | 1        |
      | Finzy low-risk investments  | 1    | 1        |
      | Can I borrow through finzy? | 1    | 1        |
      | What is Reinvest-Pro? Finzy | 1    | 1        |


#  Scenario Outline: Get position of  finzy website ​appears  ​in ​a google ​search
#    When user search "<SearchKeyword>" as a google web  search
#    Then user get the position on google search
#    Examples:
#      | SearchKeyword                                                                                              |
#      | rbi approved p2p lending companies                                                                         |
#      | nbfc-p2p registered with reserve bank of india                                                             |
#      | nbfc-p2p lowest interest rate india                                                                        |
#      | nbfc-p2p borrowing process simple and user friendly  You can get your loan funded in as little as 48 hours |