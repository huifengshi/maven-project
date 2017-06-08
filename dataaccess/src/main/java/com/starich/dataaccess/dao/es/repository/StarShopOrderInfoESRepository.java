package com.starich.dataaccess.dao.es.repository;

import com.starich.dataaccess.domain.dbo.StarShopOrderInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import java.util.List;

/**
 * Created by Jason on 2017/3/26.
 */
public interface StarShopOrderInfoESRepository extends ElasticsearchCrudRepository<StarShopOrderInfo, Integer> {
    Page<StarShopOrderInfo> findByNickName(String nickName, Pageable pageable);
    StarShopOrderInfo findById(Integer id);
    Page<StarShopOrderInfo> findByAnchorSource(String anchorSource, Pageable pageable);
}
