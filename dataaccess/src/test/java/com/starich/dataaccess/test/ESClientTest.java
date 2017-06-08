package com.starich.dataaccess.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.carrotsearch.hppc.cursors.ObjectObjectCursor;
import com.starich.dataaccess.dao.mysql.StarShopOrderInfoDAO;
import com.starich.dataaccess.domain.dbo.StarShopOrderInfo;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsResponse;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by Jason on 2017/3/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-dataaccess-test.xml")
public class ESClientTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ESClientTest.class);

    @Autowired
    @Qualifier("esClient")
    private TransportClient transportClient;

    @Resource
    private StarShopOrderInfoDAO starShopOrderInfoDAO;

    @Test
    public void testConnection(){
        List<DiscoveryNode> discoveryNodeList = transportClient.listedNodes();
        for(DiscoveryNode discoveryNode : discoveryNodeList){
            LOGGER.info(discoveryNode.getHostAddress());
        }
    }

    @Test
    public void testESALLInsert(){
        List<StarShopOrderInfo> starShopOrderInfos = starShopOrderInfoDAO.select(new StarShopOrderInfo());
        Assert.assertNotNull(starShopOrderInfos);
        Assert.assertNotEquals(0, starShopOrderInfos.size());
        BulkRequestBuilder bulkRequest = transportClient.prepareBulk();
        LOGGER.info("starShopOrderInfos total size:" + starShopOrderInfos.size());
        int i = 0;
        for(StarShopOrderInfo starShopOrderInfo : starShopOrderInfos){
            bulkRequest.add(transportClient.prepareIndex("good_show", "starshop_order_info", starShopOrderInfo.getId().toString())
                    .setSource(JSON.toJSONString(starShopOrderInfo))
                    );
            i++;
            if(bulkRequest.numberOfActions() == 100){
                BulkResponse bulkResponse = bulkRequest.get();
                Assert.assertEquals(false, bulkResponse.hasFailures());
                bulkRequest.request().requests().clear();
                LOGGER.info("i:" + i);
            }
        }
    }

    @Test
    public void testInsertById(){

        GetResponse response = transportClient.prepareGet("good_show", "starshop_order_info", "435649").get();
        Assert.assertNotNull(response);
        if(response.isExists()) {
            DeleteResponse deleteResponse = transportClient.prepareDelete("good_show", "starshop_order_info", "435649").get();
            Assert.assertNotNull(deleteResponse);
            Assert.assertEquals(true, deleteResponse.isFound());
        }

        StarShopOrderInfo query = new StarShopOrderInfo();
        query.setId(435649);
        List<StarShopOrderInfo> starShopOrderInfos = starShopOrderInfoDAO.select(query);
        Assert.assertNotNull(starShopOrderInfos);
        Assert.assertEquals(1, starShopOrderInfos.size());
        IndexResponse indexResponse = transportClient.prepareIndex("good_show", "starshop_order_info",
                starShopOrderInfos.get(0).getId().toString())
                .setSource(JSON.toJSONString(starShopOrderInfos.get(0))).get();
        Assert.assertNotNull(indexResponse);
        Assert.assertEquals(true, indexResponse.isCreated());
    }

    @Test
    public void testESGet(){
        GetResponse response = transportClient.prepareGet("good_show", "starshop_order_info", "434689").get();
        Assert.assertNotNull(response);
        Assert.assertEquals(true, response.isExists());
        LOGGER.info(response.getSourceAsString());
        StarShopOrderInfo starShopOrderInfo = JSON.parseObject(response.getSourceAsString(), StarShopOrderInfo.class);
        LOGGER.info(JSON.toJSONString(starShopOrderInfo));
    }

    @Test
    public void testDelete(){
        GetResponse response = transportClient.prepareGet("good_show", "starshop_order_info", "434689").get();
        Assert.assertNotNull(response);
        Assert.assertEquals(true, response.isExists());
        DeleteResponse deleteResponse = transportClient.prepareDelete("good_show", "starshop_order_info", "434689").get();
        Assert.assertNotNull(deleteResponse);
        Assert.assertEquals(true, deleteResponse.isFound());
        response = transportClient.prepareGet("good_show", "starshop_order_info", "434689").get();
        Assert.assertNotNull(response);
        Assert.assertEquals(false, response.isExists());
    }

    @Test
    public void testDeleteByQueryAPI(){
        SearchResponse searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("roomId", "218564"))
                .setFrom(0).setSize(10).setExplain(true)
                .get();
        Assert.assertNotNull(searchResponse);
        Assert.assertEquals(1, searchResponse.getHits().getTotalHits());


    }

    @Test
    public void testUpdate(){
        GetResponse response = transportClient.prepareGet("good_show", "starshop_order_info", "434689").get();
        Assert.assertNotNull(response);
        Assert.assertEquals(true, response.isExists());
        StarShopOrderInfo starShopOrderInfo = JSON.parseObject(response.getSourceAsString(), StarShopOrderInfo.class);
        LOGGER.info(starShopOrderInfo.getNickName());
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("good_show");
        updateRequest.type("starshop_order_info");
        updateRequest.id("434689");
        try {
            updateRequest.doc(XContentFactory.jsonBuilder()
                    .startObject()
                    .field("nickName", "Abby李湘3")
                    .endObject());
        } catch(IOException ioe){

        }
        try {
            UpdateResponse updateResponse = transportClient.update(updateRequest).get();
            Assert.assertNotNull(updateResponse);
            Assert.assertEquals(updateResponse.isCreated(), true);
        }catch (InterruptedException ie){

        }catch (ExecutionException ee){

        }

        response = transportClient.prepareGet("good_show", "starshop_order_info", "434689").get();
        Assert.assertNotNull(response);
        Assert.assertEquals(true, response.isExists());
        starShopOrderInfo = JSON.parseObject(response.getSourceAsString(), StarShopOrderInfo.class);
        Assert.assertEquals("Abby李湘3", starShopOrderInfo.getNickName());

        try {
            transportClient.prepareUpdate("good_show", "starshop_order_info", "434689")
                    .setDoc(XContentFactory.jsonBuilder()
                            .startObject()
                            .field("nickName", "Abby李湘4")
                            .endObject())
                    .get();
        }catch (IOException ioe){

        }

        response = transportClient.prepareGet("good_show", "starshop_order_info", "434689").get();
        Assert.assertNotNull(response);
        Assert.assertEquals(true, response.isExists());
        starShopOrderInfo = JSON.parseObject(response.getSourceAsString(), StarShopOrderInfo.class);
        Assert.assertEquals("Abby李湘4", starShopOrderInfo.getNickName());
    }

    @Test
    public  void testUpsert(){

        IndexRequest indexRequest = new IndexRequest("good_show", "test", "1");
        try {
            indexRequest.source(XContentFactory.jsonBuilder()
                    .startObject()
                    .field("name", "Joe Smith")
                    .field("gender", "male")
                    .endObject());
        }catch (IOException ioe){
            Assert.fail();
        }
        UpdateRequest updateRequest = new UpdateRequest("good_show", "test", "1");
        try {
            updateRequest.doc(XContentFactory.jsonBuilder()
                    .startObject()
                        .field("name", "Jason Shi")
                        .field("gender", "male")
                    .endObject()).upsert(indexRequest);
        }catch (IOException ioe){
            Assert.fail();
        }
        try {
            transportClient.update(updateRequest).get();
        }catch (InterruptedException inter){
            Assert.fail();

        }catch (ExecutionException ee){
            Assert.fail();
        }

        GetResponse response = transportClient.prepareGet("good_show", "test", "1").get();
        Assert.assertNotNull(response);
        Assert.assertEquals(true, response.isExists());
        JSONObject jsonObject = JSONObject.parseObject(response.getSourceAsString());
        Assert.assertEquals("Jason Shi", jsonObject.getString("name"));
    }

    @Test
    public void testMultiGet(){
        MultiGetResponse multiGetItemResponses = transportClient.prepareMultiGet()
                .add("good_show", "starshop_order_info", "434698")
                .add("good_show", "starshop_order_info", "434705", "434708", "434714")
                .add("good_show", "test", "1").get();
        for(MultiGetItemResponse itemResponse : multiGetItemResponses){
            GetResponse response = itemResponse.getResponse();
            Assert.assertEquals(true, response.isExists());
        }
    }

    @Test
    public void testBulkAPI(){
        BulkRequestBuilder bulkRequestBuilder = transportClient.prepareBulk();
        try {
            bulkRequestBuilder.add(transportClient.prepareIndex("good_show", "test", "2")
                    .setSource(XContentFactory.jsonBuilder()
                            .startObject()
                            .field("name", "Helen")
                            .field("gender", "female")
                            .endObject())
            );
            bulkRequestBuilder.add(transportClient.prepareDelete("good_show", "test", "1"));
        }catch (IOException ioe){
            Assert.fail();
        }
        BulkResponse bulkResponse = bulkRequestBuilder.get();
        Assert.assertEquals(false, bulkResponse.hasFailures());
        GetResponse getResponse = transportClient.prepareGet("good_show", "test", "1").get();
        Assert.assertEquals(false, getResponse.isExists());

        getResponse = transportClient.prepareGet("good_show", "test", "2").get();
        Assert.assertEquals(true, getResponse.isExists());
    }



    @Test
    public void testBulkProcessor(){
        BulkProcessor bulkProcessor = BulkProcessor.builder(transportClient,
                new BulkProcessor.Listener() {
                    @Override
                    public void beforeBulk(long var1, BulkRequest bulkRequest) {

                    }

                    @Override
                    public void afterBulk(long var1, BulkRequest bulkRequest, BulkResponse bulkResponse) {

                    }

                    @Override
                    public void afterBulk(long var1, BulkRequest bulkRequest, Throwable throwable) {

                    }
                })
                .setBulkActions(10000)
                .setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB))
                .setFlushInterval(TimeValue.timeValueSeconds(5))
                .setConcurrentRequests(1)
                .setBackoffPolicy(
                        BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
                .build();

        List<StarShopOrderInfo> starShopOrderInfos = starShopOrderInfoDAO.select(new StarShopOrderInfo());
        Assert.assertNotNull(starShopOrderInfos);
        Assert.assertNotEquals(0, starShopOrderInfos.size());
        for(StarShopOrderInfo starShopOrderInfo : starShopOrderInfos){
            bulkProcessor.add(new IndexRequest("good_show", "starshop_order_info", starShopOrderInfo.getId().toString())
                                    .source(JSON.toJSONString(starShopOrderInfo)));
        }
        bulkProcessor.flush();
        bulkProcessor.close();
        transportClient.admin().indices().prepareRefresh().get();

        GetResponse getResponse = transportClient.prepareGet("good_show", "starshop_order_info", "434689").get();
        Assert.assertNotNull(getResponse);
        Assert.assertEquals(true, getResponse.isExists());
    }

    @Test
    public void refreshIndex(){
        transportClient.admin().indices().prepareRefresh().get();
    }

    @Test
    public void testSearch(){
        SearchResponse response = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("roomId", "238701"))
                .setPostFilter(QueryBuilders.rangeQuery("settlementPrice").gt(100))
                .setFrom(0).setSize(100).setExplain(true).get();
        Assert.assertNotNull(response);
        Assert.assertEquals(384, response.getHits().getTotalHits());
    }

    @Test
    public void testProductNameSearch(){
        SearchResponse searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.matchPhraseQuery("productName", "美宝莲")).get();
        for(SearchHit searchHit : searchResponse.getHits().getHits()){
            LOGGER.info(searchHit.getSourceAsString());
        }
    }

    @Test
    public void testAnchorSourceSearch(){
        SearchResponse searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.termQuery("anchorSource", "economy_company")).get();
        for(SearchHit searchHit : searchResponse.getHits().getHits()){
            LOGGER.info(searchHit.getSourceAsString());
        }
    }

    @Test
    public void testIntegerStringTypeSearch(){
        SearchResponse searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.matchQuery("skuNo", "1027")).get();
        LOGGER.info("matchQuery 1027:" + searchResponse.getHits().getTotalHits());

        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.matchQuery("skuNo", "1027022")).get();
        LOGGER.info("matchQuery 1027022:" + searchResponse.getHits().getTotalHits());

        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.matchQuery("skuNo", 1027022)).get();
        LOGGER.info("matchQuery 1027022:" + searchResponse.getHits().getTotalHits());


        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.matchPhraseQuery("skuNo", "1027")).get();
        LOGGER.info("matchPhraseQuery 1027:" + searchResponse.getHits().getTotalHits());

        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.matchPhraseQuery("skuNo", "1027022")).get();
        LOGGER.info("matchPhraseQuery 1027022:" + searchResponse.getHits().getTotalHits());

        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.matchPhraseQuery("skuNo", 1027022)).get();
        LOGGER.info("matchPhraseQuery 1027022:" + searchResponse.getHits().getTotalHits());




        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.termQuery("skuNo", "1027")).get();
        LOGGER.info("termQuery 1027:" + searchResponse.getHits().getTotalHits());

        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.termQuery("skuNo", "1027022")).get();
        LOGGER.info("termQuery 1027022:" + searchResponse.getHits().getTotalHits());

        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.termQuery("skuNo", 1027022)).get();
        LOGGER.info("termQuery 1027022:" + searchResponse.getHits().getTotalHits());
    }

    @Test
    public void findSkuNoDifferFromDB(){
        SearchResponse searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.termQuery("skuNo", 1027022)).get();
        LOGGER.info("query from es,total count:" + searchResponse.getHits().getTotalHits());

        StarShopOrderInfo query = new StarShopOrderInfo();
        query.setSkuNo("1027022");
        List<StarShopOrderInfo> starShopOrderInfoList = starShopOrderInfoDAO.select(query);
        LOGGER.info("query form db,total count:" + starShopOrderInfoList.size());

        for(StarShopOrderInfo starShopOrderInfo : starShopOrderInfoList){
            boolean match = false;
            for(SearchHit searchHit : searchResponse.getHits().getHits()){
                String orderInfo = searchHit.getSourceAsString();
                StarShopOrderInfo starShopOrderInfoES = JSON.parseObject(orderInfo, StarShopOrderInfo.class);
                if(starShopOrderInfo.getOrderId().equals(starShopOrderInfoES.getOrderId())
                        && starShopOrderInfo.getOrderSn().equals(starShopOrderInfoES.getOrderSn())
                        ){
                    match = true;
                    break;
                }
            }
            if(!match){
                LOGGER.info("not match:" + JSON.toJSONString(starShopOrderInfo));
            }
        }
    }

    @Test
    public void testIntegerTypeSearch(){
        SearchResponse searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.matchQuery("orderId", 799)).get();
        LOGGER.info("matchQuery 799i:" + searchResponse.getHits().getTotalHits());

        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.matchQuery("orderId", "799")).get();
        LOGGER.info("matchQuery 799s:" + searchResponse.getHits().getTotalHits());

        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.matchQuery("orderId", 799594038)).get();
        LOGGER.info("matchQuery 799594038:" + searchResponse.getHits().getTotalHits());



         searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.matchPhraseQuery("orderId", 799)).get();
        LOGGER.info("matchPhraseQuery 799i:" + searchResponse.getHits().getTotalHits());

        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.matchPhraseQuery("orderId", "799")).get();
        LOGGER.info("matchPhraseQuery 799s:" + searchResponse.getHits().getTotalHits());

        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.matchPhraseQuery("orderId", 799594038)).get();
        LOGGER.info("matchPhraseQuery 799594038:" + searchResponse.getHits().getTotalHits());




        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.termQuery("orderId", 799)).get();
        LOGGER.info("termQuery 799i:" + searchResponse.getHits().getTotalHits());

        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.termQuery("orderId", "799")).get();
        LOGGER.info("termQuery 799s:" + searchResponse.getHits().getTotalHits());

        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.termQuery("orderId", 799594038)).get();
        LOGGER.info("termQuery 799594038:" + searchResponse.getHits().getTotalHits());


    }

    @Test
    public void testStringTypeSearch(){

        SearchResponse searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.matchQuery("nickName", "熊")).get();
        LOGGER.info("matchQuery 熊:" + searchResponse.getHits().getTotalHits());

        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.matchQuery("nickName", "\"熊\"")).get();
        LOGGER.info("matchQuery \"熊\":" + searchResponse.getHits().getTotalHits());

        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.matchQuery("nickName", "熊花花_")).get();
        LOGGER.info("matchQuery 熊花花_:" + searchResponse.getHits().getTotalHits());


        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.matchQuery("nickName", "\"熊花花_\"")).get();
        LOGGER.info("matchQuery \"熊花花_\":" + searchResponse.getHits().getTotalHits());




        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.matchPhraseQuery("nickName", "熊")).get();
        LOGGER.info("matchPhraseQuery 熊:" + searchResponse.getHits().getTotalHits());

        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.matchPhraseQuery("nickName", "\"熊\"")).get();
        LOGGER.info("matchPhraseQuery \"熊\":" + searchResponse.getHits().getTotalHits());


        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.matchPhraseQuery("nickName", "熊花花_")).get();
        LOGGER.info("matchPhraseQuery 熊花花_:" + searchResponse.getHits().getTotalHits());


        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.matchPhraseQuery("nickName", "\"熊花花_\"")).get();
        LOGGER.info("matchPhraseQuery \"熊花花_\":" + searchResponse.getHits().getTotalHits());




        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.termQuery("nickName", "熊")).get();
        LOGGER.info("termQuery 熊:" + searchResponse.getHits().getTotalHits());

        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.termQuery("nickName", "\"熊\"")).get();
        LOGGER.info("termQuery \"熊\":" + searchResponse.getHits().getTotalHits());

        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.termQuery("nickName", "熊花花_")).get();
        LOGGER.info("termQuery 熊花花_:" + searchResponse.getHits().getTotalHits());


        searchResponse = transportClient.prepareSearch("good_show")
                .setTypes("starshop_order_info")
                .setQuery(QueryBuilders.termQuery("nickName", "\"熊花花_\"")).get();
        LOGGER.info("termQuery \"熊花花_\":" + searchResponse.getHits().getTotalHits());


    }


    @Test
    public void testScrollSearch(){
        QueryBuilder qb = QueryBuilders.termQuery("userId", "23487753");
        SearchResponse scrollResp = transportClient.prepareSearch("good_show")
                .setScroll(new TimeValue(60000))
                .setQuery(qb)
                .setSize(1).get();

        do{
            for(SearchHit hit : scrollResp.getHits().getHits()){
                LOGGER.info(hit.getSourceAsString());
            }
            scrollResp = transportClient.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
        }while(scrollResp.getHits().getHits().length != 0);
    }

    /**
     * 测试字段类型设置或者查询错误，造成的数据混乱
     */
    @Test
    public void testFieldMapping(){

        SearchResponse searchResponse = transportClient.prepareSearch("bde").setTypes("test").get();
        for(SearchHit searchHit : searchResponse.getHits().getHits()){
            transportClient.prepareDelete("bde", "test", searchHit.getId());
        }

        Map<String, Object> m = new HashMap<>();
        m.put("name", "jason");
        m.put("age", "20");
        IndexResponse indexResponse = transportClient.prepareIndex("bde", "test").setSource(JSON.toJSONString(m)).get();
        Assert.assertEquals(true, indexResponse.isCreated());


        searchResponse = transportClient.prepareSearch("bde").setQuery(QueryBuilders.termQuery("age", 20)).get();

        for(SearchHit searchHit : searchResponse.getHits().getHits()){
            LOGGER.info(searchHit.getSourceAsString());
        }
        /*Assert.assertEquals(0, searchResponse.getHits().getTotalHits());

        searchResponse = transportClient.prepareSearch("bde").setQuery(QueryBuilders.termQuery("name", "jason")).get();
        Assert.assertNotEquals(0, searchResponse.getHits().getTotalHits());


        searchResponse = transportClient.prepareSearch("bde").setQuery(QueryBuilders.termQuery("age", "20")).get();
        Assert.assertNotEquals(0, searchResponse.getHits().getTotalHits());*/
    }

    @Test
    public void testQuery(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "junliangh");
        jsonObject.put("friend", new JSONObject());
        jsonObject.getJSONObject("friend").put("name", "haoyaoz");

        IndexResponse indexResponse = transportClient.prepareIndex("bdetest2", "user",
                "1")
                .setSource(jsonObject.toJSONString()).get();
        Assert.assertNotNull(indexResponse);
        Assert.assertEquals(true, indexResponse.isCreated());

        transportClient.admin().indices().prepareRefresh().get();
    }

    @Test
    public void testAdmin(){
        transportClient.index(IndexRequest)
        GetSettingsResponse getSettingsResponse = transportClient.admin().indices().prepareGetSettings().get();
        for (ObjectObjectCursor<String, Settings> cursor : getSettingsResponse.getIndexToSettings()) {
            String index = cursor.key;
            Settings settings = cursor.value;
            LOGGER.info(index + " : " + settings.toString());
        }
    }
}
