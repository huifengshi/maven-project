package com.starich.dataaccess.test;

import com.alibaba.fastjson.JSON;
import com.starich.dataaccess.dao.es.repository.StarShopOrderInfoESRepository;
import com.starich.dataaccess.domain.dbo.StarShopOrderInfo;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Jason on 2017/3/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-dataaccess-test.xml")
public class ESRepositoryTest {

    @Autowired
    private StarShopOrderInfoESRepository starShopOrderInfoESRepository;


    @Test
    public void testFindByNickName(){
        PageRequest pageRequest = new PageRequest(1, 10);
        Page<StarShopOrderInfo> starShopOrderInfoPage = starShopOrderInfoESRepository.findByNickName("任", pageRequest);
        Assert.assertNotNull(starShopOrderInfoPage);
        Assert.assertNotEquals(0, starShopOrderInfoPage.getTotalElements());
    }

    @Test
    public void testFindByAnchorSource(){
        PageRequest pageRequest = new PageRequest(1,10);
        Page<StarShopOrderInfo> starShopOrderInfoPage = starShopOrderInfoESRepository.findByAnchorSource("SIGN_INDIVIDUAL_ANCHOR", pageRequest);
        Assert.assertNotNull(starShopOrderInfoPage);
        Assert.assertNotEquals(0, starShopOrderInfoPage.getTotalElements());
    }

    @Test
    public void testFindById(){
        StarShopOrderInfo starShopOrderInfo = starShopOrderInfoESRepository.findById(435649);

        Assert.assertNotNull(starShopOrderInfo);
        String newNickname = starShopOrderInfo.getNickName() + "2";
        starShopOrderInfo.setNickName(newNickname);
        starShopOrderInfoESRepository.save(starShopOrderInfo);

        starShopOrderInfo = starShopOrderInfoESRepository.findOne(435649);

        Assert.assertEquals(newNickname, starShopOrderInfo.getNickName());

    }


}
