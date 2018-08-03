/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.demo.consumer;

import com.alibaba.fastjson.JSON;
import org.apache.dubbo.demo.DemoService;
import org.apache.dubbo.entity.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class Consumer {

    public static void main(String[] args) {
        //Prevent to get IPV6 address,this way only work in debug mode
        //But you can pass use -Djava.net.preferIPv4Stack=true,then it work well whether in debug mode or not
        System.setProperty("java.net.preferIPv4Stack", "true");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/dubbo-demo-consumer.xml"});
        context.start();
        DemoService demoService = (DemoService) context.getBean("demoService"); // get remote service proxy
        User user = new User();
        user.setId(-1);
        user.setName("liss");
        user.setBirthday(new Date());

        String hello = demoService.sayHello(JSON.parseObject("{\"birthday\":\"2018-08-02\",\"name\":\"liss\",\"id\":-1}",User.class)); // call remote method
        System.out.println(JSON.toJSON(user));
        System.out.println(hello); // get result
//        while (true) {
//            try {
//                Thread.sleep(1000);
//                String hello = demoService.sayHello("world"); // call remote method
//                System.out.println(hello); // get result
//
//            } catch (Throwable throwable) {
//                throwable.printStackTrace();
//            }
//
//
//        }

    }
}
