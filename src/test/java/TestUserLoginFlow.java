import api.UserValidate;
import api.UserValidateDto;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertyUtil;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author rahul.kumar
 * @version $Id: TestUserLoginFlow.java, v 0.1 2020-02-01 12:30 rahul.kumar Exp $$
 */
public class TestUserLoginFlow {

    static {
        try {
            PropertyUtil.getInstance().load("config.properties");

        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException("Something wrong !!! Check configurations.", e);
        }
    }

    @Test(description = "Verify For user login With Valid User and password, Asserttion - responseCode = 200, Success = true")
    public void testUserValidation(){

        UserValidateDto userValidateDto = new UserValidateDto();

        PropertyUtil.getInstance().load("config.properties");

        userValidateDto.setEmail(PropertyUtil.getInstance().getValue("VALID_USERNAME"));
        userValidateDto.setPassword(PropertyUtil.getInstance().getValue("VALID_PASSWORD"));
        UserValidate userValidate = new UserValidate(userValidateDto);
        Response response = userValidate.execute();
        JsonPath jsonPath = response.jsonPath();

        //Verify for the response code of the error
        assertThat(response.getStatusCode(), is(200));
        //Verify that success parameter is coming in response
        Assert.assertTrue((Boolean) jsonPath.get("success"));
        Assert.assertEquals(jsonPath.get("data.token"),"NPONSUJA189CZUNA");

        //Verify that success parameter value is true in response
      //  Assert.assertEquals( jsonPath.get("success"),true);

    }

    @Test(description = "Verify for token data is coming in every response with valid userName and password")
    public void testForTokenData(){
        UserValidateDto userValidateDto = new UserValidateDto();

        userValidateDto.setEmail(PropertyUtil.getInstance().getValue("VALID_USERNAME"));
        userValidateDto.setPassword("Qwerty123");
        UserValidate userValidate = new UserValidate(userValidateDto);
        Response response = userValidate.execute();
        JsonPath jsonPath = response.jsonPath();

        //Verify for the response code of the error
        assertThat(response.getStatusCode(), is(200));
    }

    @Test(description = "Verify when email Id and password are  not correct in the request")
    public void testForInvalidUserNameAndPassword(){
        UserValidateDto userValidateDto = new UserValidateDto();

        userValidateDto.setEmail("test1@gmail.com");
        userValidateDto.setPassword("Qwerty123");
        UserValidate userValidate = new UserValidate(userValidateDto);
        Response response = userValidate.execute();
        JsonPath jsonPath = response.jsonPath();

        //Verify for the response code of the error
        assertThat(response.getStatusCode(), is(200));
        Assert.assertFalse((Boolean) jsonPath.get("success"));
        Assert.assertEquals(jsonPath.get("errorMessage"),"Invalid Credentials.");
    }

    @Test(description = "Verify if request Parameters does not contains password")
    public void testUserValidateApiWithoutPasswordParameter(){

        UserValidateDto userValidateDto = new UserValidateDto();

        userValidateDto.setEmail("test1@gmail.com");
       // userValidateDto.setPassword("Qwerty123");
        UserValidate userValidate = new UserValidate(userValidateDto);
        Response response = userValidate.execute();
        JsonPath jsonPath = response.jsonPath();

        //Verify for the response code of the error
        assertThat(response.getStatusCode(), is(400));
        Assert.assertEquals(jsonPath.get("error"),"Bad Request");

    }

    @Test(description = "Verify if request Parameters does not contains email")
    public void testUserValidateApiWithoutEmailParameter(){

        UserValidateDto userValidateDto = new UserValidateDto();

       // userValidateDto.setEmail("test1@gmail.com");
        userValidateDto.setPassword("Qwerty123");
        UserValidate userValidate = new UserValidate(userValidateDto);
        Response response = userValidate.execute();
        JsonPath jsonPath = response.jsonPath();

        //Verify for the response code of the error
        assertThat(response.getStatusCode(), is(400));
        Assert.assertEquals(jsonPath.get("error"),"Bad Request");

    }

}