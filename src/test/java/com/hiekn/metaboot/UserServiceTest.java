package com.hiekn.metaboot;

import com.hiekn.boot.web.jersey.model.PageModel;
import com.hiekn.boot.web.jersey.result.RestData;
import com.hiekn.metaboot.bean.UserBean;
import com.hiekn.metaboot.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.jdbc.Sql;

public class UserServiceTest extends MetaBootApplicationTests{

    private static final Log logger = LogFactory.getLog(UserServiceTest.class);

    @Autowired
	private UserService userService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private TransportClient client;

	@Test
	public void testAssert(){
		Assert.assertSame(userService,userService);
		Assert.assertEquals(1, 1);
		Assert.assertTrue (2 < 3);
		Assert.assertFalse(2 > 3);
		Assert.assertNotNull(userService);
		Assert.assertNull(userService);
    }

    @Test
    @Sql(statements = "insert into user (id,email) values (1,'dh@gamil.com')")
    public void testSql(){
        RestData<UserBean> rd =  userService.listByPage(new PageModel(1,10),null);
        boolean flag =false;
        for (UserBean o : rd.getRsData()) {
            if("dh@gamil.com".equals(o.getEmail())){
                flag = true;
                break;
            }
        }
        Assert.assertTrue (flag);
    }

    @Test
    public void mongoTemplateTest(){
        RestData<UserBean> rd =  userService.listByPage(new PageModel(1,10),null);
        mongoTemplate.insert(rd.getRsData(),"table");
    }

    @Test
    public void redisTemplateTest(){
        stringRedisTemplate.opsForValue().set("key","value");
    }

    @Test
    public void elasticsearchTest(){
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch("x").setTypes("y");
        SearchResponse response = searchRequestBuilder
                .setQuery(QueryBuilders.termQuery("query", 1))
                .get();
        response.getHits().forEach(hit -> logger.info(hit.getSourceAsMap()));

    }

}
