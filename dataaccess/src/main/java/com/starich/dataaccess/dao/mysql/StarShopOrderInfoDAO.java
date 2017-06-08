package com.starich.dataaccess.dao.mysql;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.starich.dataaccess.domain.dbo.StarShopOrderInfo;

public interface StarShopOrderInfoDAO {

    int insert(@Param("pojo") StarShopOrderInfo pojo);

    int insertList(@Param("pojos") List< StarShopOrderInfo> pojo);

    List<StarShopOrderInfo> select(@Param("pojo") StarShopOrderInfo pojo);

    int update(@Param("pojo") StarShopOrderInfo pojo);

}
