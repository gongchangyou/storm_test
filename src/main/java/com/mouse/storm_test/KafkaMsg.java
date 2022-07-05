package com.mouse.storm_test;

/**
 * @Desc kafka消息格式
 * @Author xujishan
 * @Date 2020/6/2 下午2:35
 * @Version 1.0
 **/
public class KafkaMsg extends BasicMessage{

    /**
     * id : 7500004723
     * type : 1101
     * operation : 1
     * data : {"assetId":"7500004723","identity":"0"}
     * time : 2020-06-02 14:05:52
     * version : 1591077952492
     * transId : 1101-b10057a7-0cff-4aef-9b84-283501d36932
     * method : KAFKA
     */

    private String data;
    private String method;
    private String isResend; //是否是重发消息

    public String getIsResend() {
        return isResend;
    }

    public void setIsResend(String isResend) {
        this.isResend = isResend;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}
