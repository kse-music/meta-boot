package com.hiekn.metaboot;

import cn.hiboot.mcn.core.model.result.RestResp;
import com.hiekn.metaboot.bean.po.User;
import com.hiekn.metaboot.bean.search.UserSearch;
import com.hiekn.metaboot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class UserServiceTest extends MetaBootApplicationTest {

    @Autowired
	private UserService userService;

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
        RestResp<List<User>> rd =  userService.page(new User(),userBean);
        boolean flag =false;
        for (User o : rd.getData()) {
            if("dh@gamil.com".equals(o.getEmail())){
                flag = true;
                break;
            }
        }
        assertTrue (flag);
    }

}
