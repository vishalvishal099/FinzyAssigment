Feature: Test ​position ​of if finzy website is not appears at first position in  a google search

  Scenario Outline: Get position of  finzy website ​appears  ​in ​a google ​search
  if finzy website is not appears at first position
    When user search "<SearchKeyword>" as a google web  search
    Then user get the position on google search
    Examples:
      | SearchKeyword                                                                                              |
      | rbi approved p2p lending companies                                                                         |
      | nbfc-p2p registered with reserve bank of india                                                             |
      | nbfc-p2p lowest interest rate india                                                                        |
      | nbfc-p2p borrowing process simple and user friendly  You can get your loan funded in as little as 48 hours |

