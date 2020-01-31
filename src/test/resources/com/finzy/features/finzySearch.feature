Feature: Test Finzy website ​appears

  Scenario Outline: Verify finzy website ​appears ​first ​in ​a ​google web ​search.
    When user search "<SearchKeyword>" as a google web  search
    Then finzy website should appears at <page> and <position>
    Examples:
      | SearchKeyword               | page | position |
      | low-risk investments Finzy  | 1    | 1        |
      | BORROW Finzy                | 1    | 1        |
      | low-NBFC-P2P  Finzy         | 1    | 1        |
      | Finzy low-risk investments  | 1    | 1        |
      | Can I borrow through finzy? | 1    | 1        |
      | What is Reinvest-Pro? Finzy | 1    | 1        |
     #| Finzy peer to peer lending  | 1    | 1        |


  Scenario Outline: Get position of  finzy website ​appears  ​in ​a google ​search
  if finzy website is not appears at first position
    When user search "<SearchKeyword>" as a google web  search
    Then user get the position on google search
    Examples:
      | SearchKeyword                      |
      | rbi approved p2p lending companies |
#      | low-NBFC-P2P  Finzy         |
#      | Finzy low-risk investments  |
#      | Can I borrow through finzy? |
#      | What is Reinvest-Pro? Finzy |
#      | sdfkasldfkjasfdasl asdfa123344425235235325   |
#      | indian companies in peer to peer lending |