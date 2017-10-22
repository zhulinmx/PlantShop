package framework;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;  
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  
  
import com.alibaba.fastjson.JSON;
import com.mapper.user.persistent.User;
import com.service.user.UserService;  
  
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath:config/spring-mybatis.xml"})  
public class TestMyBatis {  
    private static Logger logger = Logger.getLogger(TestMyBatis.class);  
    @Resource  
    private UserService userService;  
  
    @Test  
    public void test1() {  
        User user = userService.getUserById(1);  
        logger.info(JSON.toJSONString(user));
        System.out.println(JSON.toJSONString(user));
    }  
}
