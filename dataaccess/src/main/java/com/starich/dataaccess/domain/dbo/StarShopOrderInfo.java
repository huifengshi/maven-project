package com.starich.dataaccess.domain.dbo;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jason on 2017/3/20.
 */
@Document(indexName = "good_show", type = "starshop_order_info")
public class StarShopOrderInfo {

    private Integer id;                 //主键ID

    private String roomId;              //直播间ID

    private String userId;              //主播ID

    private String nickName;            //主播昵称

    private String anchorSource;        //主播来源

    private Long orderSn;               //订单sn

    private Long orderId;               //订单ID

    private String saleFrom;            //销售来源

    private String saleType;            //销售类型

    private String saleLabel;           //销售标签

    private String platform;            //分成平台

    private Integer productId;          //商品ID

    private String productName;         //商品名称

    private String skuNo;               //商品SKU

    private Integer brandId;            //品牌ID

    private String brandName;           //品牌名称

    private Integer merchantId;         //商家ID

    private Byte salePlatform;          //售卖平台0:主站-jumei 1:海淘-jumeiglobal

    private Byte saleMode;              //售卖模式0:自营-retail, 1:POP-pop, 2:FBJ-fbj

    private String dealHashId;          //HASHID

    private Byte isBom;                 //是否是bom sku,0不是,1是

    private String skuCategory;         //商品分类

    private String payer;               //购买人UID

    private Byte orderStatus;           //订单状态 已确认1(源状态为2) 已取消2(源状态为0，10，12)

    private Integer quantity;           //购买数量

    private BigDecimal settlementPrice; //实际支付金额

    private Date paymentTime;           //订单支付时间

    private Date payDay;                //订单支付日期

    private Date cancelTime;            //全订单取消时间

    private BigDecimal feeRatio;        //佣金比例

    private Integer refundQuantity;     //父商品退货数量

    private BigDecimal refundAmount;    //退货父SKU总金额

    private BigDecimal dividSkuAmount;  //可结算分成父SKU商品总金额

    private BigDecimal feeAmount;       //父SKU佣金总金额

    private Integer uniqueKey;          //cps唯一标识

    private Byte settlementStatus;      //结算状态：1、待结算 2、已结算

    private Date createTime;            //创建时间

    private Date updateTime;            //更新时间
    /*1.9.5需求#122004：数据同步新加字段*/
    private Integer shippingSystemId;           //发货仓库ID
    private Long shippingNo;                    //包裹流水号
    private Date shippingInitialTime;           //包裹创建时间
    private Date deliverTime;                   //发货时间
    private Integer isCancelBeforeDeliver;      //是否发货前退货标识
    private Integer orderType;                     //订单类型
    private BigDecimal dealPrice;               //原始价格
    private Byte settlementOnlineOffline;       //线下结算，还是线上结算
    private Date commissionTime;                //可分成时间
    private Integer commissionStatus;              //可分成状态
    private Integer merchantSettlementStatus;      //商家结算状态，0:待结算,1:结算中,2:已结算
    private String merchantSettlementBillNo;    //商家结算单号



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId == null ? null : roomId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAnchorSource() {
        return anchorSource;
    }

    public void setAnchorSource(String anchorSource) {
        this.anchorSource = anchorSource;
    }

    public Long getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(Long orderSn) {
        this.orderSn = orderSn;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getSaleFrom() {
        return saleFrom;
    }

    public void setSaleFrom(String saleFrom) {
        this.saleFrom = saleFrom;
    }

    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    public String getSaleLabel() {
        return saleLabel;
    }

    public void setSaleLabel(String saleLabel) {
        this.saleLabel = saleLabel;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(String skuNo) {
        this.skuNo = skuNo == null ? null : skuNo.trim();
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Byte getSalePlatform() {
        return salePlatform;
    }

    public void setSalePlatform(Byte salePlatform) {
        this.salePlatform = salePlatform;
    }

    public Byte getSaleMode() {
        return saleMode;
    }

    public void setSaleMode(Byte saleMode) {
        this.saleMode = saleMode;
    }

    public String getDealHashId() {
        return dealHashId;
    }

    public void setDealHashId(String dealHashId) {
        this.dealHashId = dealHashId == null ? null : dealHashId.trim();
    }

    public Byte getIsBom() {
        return isBom;
    }

    public void setIsBom(Byte isBom) {
        this.isBom = isBom;
    }

    public String getSkuCategory() {
        return skuCategory;
    }

    public void setSkuCategory(String skuCategory) {
        this.skuCategory = skuCategory == null ? null : skuCategory.trim();
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer == null ? null : payer.trim();
    }

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSettlementPrice() {
        return settlementPrice;
    }

    public void setSettlementPrice(BigDecimal settlementPrice) {
        this.settlementPrice = settlementPrice;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getPayDay() {
        return payDay;
    }

    public void setPayDay(Date payDay) {
        this.payDay = payDay;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public BigDecimal getFeeRatio() {
        return feeRatio;
    }

    public void setFeeRatio(BigDecimal feeRatio) {
        this.feeRatio = feeRatio;
    }

    public Integer getRefundQuantity() {
        return refundQuantity;
    }

    public void setRefundQuantity(Integer refundQuantity) {
        this.refundQuantity = refundQuantity;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public BigDecimal getDividSkuAmount() {
        return dividSkuAmount;
    }

    public void setDividSkuAmount(BigDecimal dividSkuAmount) {
        this.dividSkuAmount = dividSkuAmount;
    }

    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    public Integer getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(Integer uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public Byte getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(Byte settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getShippingSystemId() {
        return shippingSystemId;
    }

    public void setShippingSystemId(Integer shippingSystemId) {
        this.shippingSystemId = shippingSystemId;
    }

    public Long getShippingNo() {
        return shippingNo;
    }

    public void setShippingNo(Long shippingNo) {
        this.shippingNo = shippingNo;
    }

    public Date getShippingInitialTime() {
        return shippingInitialTime;
    }

    public void setShippingInitialTime(Date shippingInitialTime) {
        this.shippingInitialTime = shippingInitialTime;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public Integer getIsCancelBeforeDeliver() {
        return isCancelBeforeDeliver;
    }

    public void setIsCancelBeforeDeliver(Integer isCancelBeforeDeliver) {
        this.isCancelBeforeDeliver = isCancelBeforeDeliver;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public BigDecimal getDealPrice() {
        return dealPrice;
    }

    public void setDealPrice(BigDecimal dealPrice) {
        this.dealPrice = dealPrice;
    }

    public Date getCommissionTime() {
        return commissionTime;
    }

    public void setCommissionTime(Date commissionTime) {
        this.commissionTime = commissionTime;
    }

    public Integer getCommissionStatus() {
        return commissionStatus;
    }

    public void setCommissionStatus(Integer commissionStatus) {
        this.commissionStatus = commissionStatus;
    }

    public Integer getMerchantSettlementStatus() {
        return merchantSettlementStatus;
    }

    public void setMerchantSettlementStatus(Integer merchantSettlementStatus) {
        this.merchantSettlementStatus = merchantSettlementStatus;
    }

    public String getMerchantSettlementBillNo() {
        return merchantSettlementBillNo;
    }

    public void setMerchantSettlementBillNo(String merchantSettlementBillNo) {
        this.merchantSettlementBillNo = merchantSettlementBillNo;
    }


    public Byte getSettlementOnlineOffline() {
        return settlementOnlineOffline;
    }

    public void setSettlementOnlineOffline(Byte settlementOnlineOffline) {
        this.settlementOnlineOffline = settlementOnlineOffline;
    }

}
