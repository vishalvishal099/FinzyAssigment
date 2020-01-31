Feature: Test ​position ​of if finzy website is not appears at first position in  a google search

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
