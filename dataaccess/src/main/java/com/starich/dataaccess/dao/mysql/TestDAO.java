package com.starich.dataaccess.dao.mysql;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.starich.dataaccess.domain.dbo.TestDO;
import org.springframework.stereotype.Repository;

public interface TestDAO {
    int insert(@Param("pojo") TestDO pojo);

    int insertSelective(@Param("pojo") TestDO pojo);

    int insertList(@Param("pojos") List<TestDO> pojo);

    int update(@Param("pojo") TestDO pojo);
}
