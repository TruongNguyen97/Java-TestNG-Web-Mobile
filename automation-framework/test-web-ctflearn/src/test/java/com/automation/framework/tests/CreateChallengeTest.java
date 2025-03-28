package com.automation.framework.tests;
import com.automation.framework.models.Account;
import com.automation.framework.models.Challenge;
import com.automation.framework.pages.DetailChallengePage;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.javafaker.Faker;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.*;

public class CreateChallengeTest extends BaseTest {
    private Account account;

    public CreateChallengeTest() throws JsonParseException, JsonMappingException, IOException {
        super();
        account = accountDataMap.get("account_create_challenge");
    }

    @DataProvider(name = "newChallengeData")
    public Object[] challengeDataProvider() {
        return new Object[]{
            createNewChallenge()
        };
    }

    public Challenge createNewChallenge() {
        Faker faker = new Faker();
        return new Challenge(){
            {
                setEvent("CTFlearn");
                setTitle("Challenge Title Test " + faker.date().hashCode());
                setFlag("CTFlearn{Test" + faker.number().digit() + "}");
                setDescription(faker.lorem().sentence(100));
                setCategory("Programming");
                setPoints("" + faker.number().numberBetween(1, 10) * 10);
                setCategory("Programming");
                setHowToSolve(faker.lorem().sentence(50));
            }
        };
    }

    @Test(dataProvider = "newChallengeData")
    public void testCreateChallengeSuccessfully(Challenge newChallenge) {
        DetailChallengePage detailChallengePage = 
            homePage
                .clickLoginLink()
                .loginWithValidAccount(account)
                .getNavigationBar()
                .clickToCreateChallenge()
                .createChallenge(newChallenge)
                .getNavigationBar()
                .clickToMyChallenges()
                .findAndClickToChallengeTitle(newChallenge.getTitle());
            
        Challenge actualChallengeData = detailChallengePage.getChallengeDetail();

        Assert.assertEquals(newChallenge.getTitle(), actualChallengeData.getTitle());
        Assert.assertEquals(newChallenge.getDescription(), actualChallengeData.getDescription());
        Assert.assertEquals(newChallenge.getCategory(), actualChallengeData.getCategory());
        Assert.assertEquals(newChallenge.getPoints(), actualChallengeData.getPoints());

        detailChallengePage
            .getNavigationBar()
            .clickToLogout();
    }

}
