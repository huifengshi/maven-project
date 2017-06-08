package com.starich.dataaccess.test;

import com.starich.dataaccess.dao.mysql.TestDAO;
import com.starich.dataaccess.domain.dbo.TestDO;
import org.elasticsearch.client.transport.TransportClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Jason on 2017/3/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-dataaccess-test.xml")
public class MybatisTest {

    @Resource
    private TestDAO testDAO;


    @Test
    public void testInsert(){
        TestDO testDO = new TestDO();
        testDO.setAge(21);
        testDO.setName("Bill Gates");
        Assert.assertEquals(1,testDAO.insert(testDO));
    }



}
