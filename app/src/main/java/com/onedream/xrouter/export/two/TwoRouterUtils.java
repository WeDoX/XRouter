package com.onedream.xrouter.export.two;

import com.onedream.xrouter_api.XRouter;

public class TwoRouterUtils {

    public static void navigationTwoPage(String name){
        XRouter.build(TwoRouterTable.PAGE_TWO).withString(TwoRouterTable.KEY_TWO_NAME, name).navigation();
    }
}
