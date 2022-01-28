package com.hiekn.metaboot;

import cn.hiboot.mcn.core.model.result.RestResp;
import com.hiekn.metaboot.bean.po.UserBean;
import com.hiekn.metaboot.bean.search.UserSearch;
import com.hiekn.metaboot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class UserServiceTest extends MetaBootApplicationTest {

    @Autowired
	private UserService userService;

    @Autowired
    private MongoTemplate mongoTemplate;

	@Test
	public void testAssert(){
        assertSame(userService,userService);
        assertNotNull(userService);
    }

    @Test
    @Sql(statements = "insert into user (id,email) values (1,'dh@gamil.com')")
    public void testSql(){
        UserSearch userBean = new UserSearch();
        userBean.setPageNo(1);
        userBean.setPageSize(10);
        RestResp<List<UserBean>> rd =  userService.page(new UserBean(),userBean);
        boolean flag =false;
        for (UserBean o : rd.getData()) {
            if("dh@gamil.com".equals(o.getEmail())){
                flag = true;
                break;
            }
        }
        assertTrue (flag);
    }

    @Test
    public void mongoTemplateTest(){
        UserSearch userBean = new UserSearch();
        userBean.setPageNo(1);
        userBean.setPageSize(10);
        RestResp<List<UserBean>> rd =  userService.page(new UserBean(),userBean);
        mongoTemplate.insert(rd.getData(),"table");
    }

    @Test
    public void redisTemplateTest(){
//        stringRedisTemplate.opsForValue().set("key","value");
    }

}
