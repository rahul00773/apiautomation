
package api;

import Config.LocalConfig;
import base.BaseApi;
import base.MethodType;
import io.restassured.http.ContentType;


/**
 * @author rahul.kumar
 * @version $Id: UserValidate.java, v 0.1 2020-02-01 12:15 rahul.kumar Exp $$
 */
public class UserValidate extends BaseApi {

    public  UserValidate(UserValidateDto userValidateDto){

        setMethod(MethodType.POST);
        getRequestSpecBuilder().setContentType(ContentType.JSON);
        getRequestSpecBuilder().setBasePath("/user/validate");
        getRequestSpecBuilder().setAccept(ContentType.JSON);
        getRequestSpecBuilder().setBaseUri(LocalConfig.BASE_URL);
        getRequestSpecBuilder().setBody(userValidateDto);
    }

}