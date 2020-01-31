package com.finzy.steps;

import com.finzy.pages.finzySearch.GoogleSearch;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.net.MalformedURLException;

public class FinzyWebAppears {
    GoogleSearch googleSearch = new GoogleSearch();
    public FinzyWebAppears() throws MalformedURLException {
    }

    @When("user search {string} as a google web  search")
    public void userSearchAsAGoogleWebSearch(String input) throws InterruptedException {
        googleSearch.searchKeywordOverGoogle(input);
    }

    @Then("finzy website should appears at {int} and {int}")
    public void finzy_website_should_appears_at(Integer page, Integer position) {
        googleSearch.verifyPosition(page, position);
    }


    @Then("user get the position on google search")
    public void userGetThePositionOnGoogleSearch() {
        googleSearch.getPosition();
    }


}
