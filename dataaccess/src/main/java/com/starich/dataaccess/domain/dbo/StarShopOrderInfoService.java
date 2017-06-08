package com.starich.dataaccess.domain.dbo;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.starich.dataaccess.dao.mysql.StarShopOrderInfoDAO;

@Service
public class StarShopOrderInfoService {

    @Resource
    private StarShopOrderInfoDAO starShopOrderInfoDao;

    public int insert(StarShopOrderInfo pojo){
        return starShopOrderInfoDao.insert(pojo);
    }

    public int insertList(List< StarShopOrderInfo> pojos){
        return starShopOrderInfoDao.insertList(pojos);
    }

    public List<StarShopOrderInfo> select(StarShopOrderInfo pojo){
        return starShopOrderInfoDao.select(pojo);
    }

    public int update(StarShopOrderInfo pojo){
        return starShopOrderInfoDao.update(pojo);
    }

}
