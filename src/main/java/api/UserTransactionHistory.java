
package api;

import Config.LocalConfig;
import base.BaseApi;
import base.MethodType;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


/**
 * @author rahul.kumar
 * @version $Id: UserTransactionHistory.java, v 0.1 2020-02-01 13:09 rahul.kumar Exp $$
 */
public class UserTransactionHistory  {

    private BaseApi api;
    public UserTransactionHistory(String token){

        BaseApi api = new BaseApi();
        api.setMethod(MethodType.GET);
        api.getRequestSpecBuilder().setContentType(ContentType.JSON);
        api.getRequestSpecBuilder().setBasePath("/user/txn/history" + "?token=" + token);
        api.getRequestSpecBuilder().setBaseUri(LocalConfig.BASE_URL);
        this.api = api;
        System.out.println(this.api);
    }

    public Response execute() {
        return this.api.execute();
    }
}