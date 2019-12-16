package com.demo.jfinal.hello;

import com.demo.jfinal.handler.Handler1;
import com.demo.jfinal.model.User.User;
import com.jfinal.aop.Aop;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.jfinal.ext.interceptor.POST;

import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author TMW
 * @version 1.0
 * @date 2019/9/10 21:47
 */
public class HelloController extends Controller {
    // @Before(MyIntercepter.class)
    public void index() {
        System.out.println(getPara());
        System.out.println(getParaToInt());
        // getBean()
        // render("/AdminLTE-master/index.html");
        renderText("hello");
        Handler1 handler1 = Aop.get(Handler1.class);
        Proxy.newProxyInstance(this.getClass().getClassLoader(), null, (proxy, method, args) -> method.invoke(proxy, args));
        Arrays.asList("1", "2", "3").forEach(System.out::println);
    }

    public void testAction(String name, String password) {
        System.out.println(name + "-----" + password);
        var as = name;
        System.out.println(as);
        renderText("hello");
    }

    // @Before(POST.class)
    public void testUserAction(@Para("") User user) {
        System.out.println(user);
        renderText("user");
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        User user = User.class.newInstance();
        System.out.println(user);
    }
}
