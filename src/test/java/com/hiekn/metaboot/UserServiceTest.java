package com.hiekn.metaboot;

import com.hiekn.boot.autoconfigure.base.model.result.RestData;
import com.hiekn.metaboot.bean.UserBean;
import com.hiekn.metaboot.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.Assert.*;

public class UserServiceTest extends MetaBootApplicationTest {

    @Autowired
	private UserService userService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


	@Test
	public void testAssert(){
        assertSame(userService,userService);
        assertTrue (2 < 3);
        assertFalse(2 > 3);
        assertNotNull(userService);
        assertNull(userService);
    }

    @Test
    @Sql(statements = "insert into user (id,email) values (1,'dh@gamil.com')")
    public void testSql(){
        UserBean userBean = new UserBean();
        userBean.setPageNo(1);
        userBean.setPageSize(10);
        RestData<UserBean> rd =  userService.listByPage(userBean);
        boolean flag =false;
        for (UserBean o : rd.getRsData()) {
            if("dh@gamil.com".equals(o.getEmail())){
                flag = true;
                break;
            }
        }
        assertTrue (flag);
    }

    @Test
    public void mongoTemplateTest(){
        UserBean userBean = new UserBean();
        userBean.setPageNo(1);
        userBean.setPageSize(10);
        RestData<UserBean> rd =  userService.listByPage(userBean);
        mongoTemplate.insert(rd.getRsData(),"table");
    }

    @Test
    public void redisTemplateTest(){
        stringRedisTemplate.opsForValue().set("key","value");
    }

}
