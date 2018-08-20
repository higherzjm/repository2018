package com.j2ee.servlet.listeners;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Created by zjm on 2018/2/7.
 */
public class My_HttpSessionAttributeListener implements HttpSessionAttributeListener{
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        String userName= (String) event.getSession().getAttribute("username");
        System.out.println("新建session userName:"+userName);
        event.getSession().setAttribute("username","----------->学生 "+userName);

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        String userName= (String) event.getSession().getAttribute("userName");
        System.out.println("清楚session userName:"+userName);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

    }

}
