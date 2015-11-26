package com.fpt.router.web.action.common;

import com.fpt.router.artifacter.config.Config;
import com.fpt.router.web.action.api.MultiPointAction;
import com.fpt.router.web.action.api.TwoPointRouteAction;
import com.fpt.router.web.config.ApplicationContext;

/**
 * Purpose:
 * Created by Huynh Quang Thao on 9/27/15.
 */
public class ActionFactory implements IActionFactory {

    @Override
    public IAction getAction(ApplicationContext context) {
        String actionCommand = context.getParameter("action");
        IAction action = null;

        // handle link such as localhost/qna/login.jsp
        // forward to WEB-INF/login.jsp
        String url = context.getServletPath();

        if (url.equals(URL.API.TWO_POINT)) {
            action = new TwoPointRouteAction();
        } else if (url.equals(URL.API.MUlTI_POINT)) {
            action = new MultiPointAction();
        } else {
            context.setAttribute(Config.WEB.DIRECT_PAGE_ATTRIBUTE, "WEB-INF" + "/hello.html");
            action = new DirectPageAction();
        }


        // handle "action" parameter that end with jsp. will go directly to jsp page
        /**
         * if (parameter.length() >= 4) { String lastStr =
         * parameter.substring(parameter.length() - 4, parameter.length()); if
         * (lastStr.equals(".jsp")) { return new JSPPageAction(); } }
         */

        System.out.println("Action Command: " + actionCommand);

        return action;
    }
}
