
package base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Reporter;
import report.Report;


import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static io.restassured.RestAssured.given;

/**
 * @author rahul.kumar
 * @version $Id: BaseApi.java, v 0.1 2020-02-01 11:39 rahul.kumar Exp $$
 */
public class BaseApi {

    private RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    private MethodType method;

    public MethodType getMethod() {
        return method;
    }


    public void setMethod(MethodType method) {
        this.method = method;
    }

    public RequestSpecBuilder getRequestSpecBuilder() {
        return requestSpecBuilder;
    }

    public Response execute() {
        RequestSpecification requestSpecification = requestSpecBuilder.build();
        Response response;

        RestAssured.defaultParser = Parser.JSON;
        //RestAssuredConfig config = new RestAssuredConfig().build();
        switch (method) {
            case GET:
                response = given().spec(requestSpecification).when().get();
                break;
            case POST:
                response = given().spec(requestSpecification).when().post();
                break;
            case PUT:
                response = given().spec(requestSpecification).when().put();
                break;
            case DELETE:
                response = given().spec(requestSpecification).when().delete();
                break;
            case PATCH:
                response = given().spec(requestSpecification).when().patch();
                break;
            default:
                throw new RuntimeException("API method not specified");

        }
        printResponse(response);
        return response;
    }




    private void printResponse(Response response) {
        String contentType = response.contentType();

        if (contentType.toLowerCase().contains("text/html") || contentType.toLowerCase().contains("text/plain")) {
            final DateFormat timeFormat = new SimpleDateFormat("MM.dd.yyyy HH-mm-ss");
            final String fileName = Reporter.getCurrentTestResult().getMethod().getMethodName() + "_" + timeFormat.format(new Date()) + ".html";

            String outputDir = Reporter.getCurrentTestResult().getTestContext().getOutputDirectory();
            outputDir = outputDir.substring(0, outputDir.lastIndexOf(File.separator)) + "/html";

            File file = new File(outputDir + File.separator + fileName);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            PrintWriter writer = null;

            try {
                file.createNewFile();
                writer = new PrintWriter(file);
                writer.write(response.asString());
                writer.flush();

            } catch (Throwable e) {
                throw new RuntimeException(e);
            } finally {
                writer.close();
            }
        } else {

        }

    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseApi{");
        sb.append("requestSpecBuilder=").append(requestSpecBuilder);
        sb.append(", method=").append(method);
        sb.append('}');
        return sb.toString();
    }
}