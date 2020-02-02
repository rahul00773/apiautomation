
package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

/**
 * @author rahul.kumar
 * @version $Id: Extent.java, v 0.1 2020-02-01 11:46 rahul.kumar Exp $$
 */
public class Extent {

    public static ThreadLocal<ExtentTest> TEST = new ThreadLocal<ExtentTest>();
    public static ExtentReports REPORT =  new ExtentReports();
}