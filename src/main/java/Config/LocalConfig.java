/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package Config;


import utils.PropertyUtil;

/**
 * @author rahul.kumar
 * @version $Id: LocalConfig.java, v 0.1 2020-02-01 12:19 rahul.kumar Exp $$
 */
public class LocalConfig {

    public static final String BASE_URL;


    static {
        try {
            PropertyUtil.getInstance().load("config.properties");
            BASE_URL = PropertyUtil.getInstance().getValue("BASE_URL");


        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException("Something wrong !!! Check configurations.", e);
        }
    }
}