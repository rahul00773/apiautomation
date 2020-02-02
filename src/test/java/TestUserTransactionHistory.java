import api.UserTransactionHistory;
import api.UserValidate;
import api.UserValidateDto;
import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author rahul.kumar
 * @version $Id: TestUserTransactionHistory.java, v 0.1 2020-02-01 13:08 rahul.kumar Exp $$
 */
public class TestUserTransactionHistory {


    @Test(description = "Verify for userTransactionHistory With Valid Login User")
    public void testUserTxnHistory(){

        UserValidateDto userValidateDto = new UserValidateDto();

        userValidateDto.setEmail("test@gmail.com");
        userValidateDto.setPassword("Qwerty123");
        UserValidate userValidate = new UserValidate(userValidateDto);
        Response response = userValidate.execute();
        JsonPath jsonPath = response.jsonPath();

        //Verify for the response code of the error
        assertThat(response.getStatusCode(), is(200));

        // get token after user login
        String token = jsonPath.getString("data.token");

        UserTransactionHistory userTransactionHistory = new UserTransactionHistory(token);
        Response responseForUserTxn = userTransactionHistory.execute();
        JsonPath jsonPathForUserTxn = responseForUserTxn.jsonPath();

        assertThat(responseForUserTxn.getStatusCode(), is(200));
        Assert.assertTrue((Boolean) jsonPathForUserTxn.get("success"));

        String data  = jsonPathForUserTxn.get("data");
        JsonObject jsonObject = new JsonObject();
        jsonObject.getAsJsonArray(data);

    }


    @Test(description = "Verify for userTransactionHistory With invalid token")
    public void testUserTxnHistoryWithInvalidToken(){


        UserTransactionHistory userTransactionHistrory = new UserTransactionHistory("abdndsd");
        Response responseForUserTxn = userTransactionHistrory.execute();
        JsonPath jsonPathForUserTxn = responseForUserTxn.jsonPath();

        assertThat(responseForUserTxn.getStatusCode(), is(200));
        Assert.assertFalse((Boolean) jsonPathForUserTxn.get("success"));
        Assert.assertEquals(jsonPathForUserTxn.get("errorMessage"),"Invalid Token.");

    }
}