package com.mouse.storm_test;

import java.io.Serializable;

/**
 * @Desc 消息基类
 * @Author xujishan
 * @Date 2020/8/25 下午3:26
 * @Version 1.0
 **/
public class BasicMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 消息ID
     *
     * @return
     */
    private String transId;
    /**
     * 消息内容ID
     *
     * @return
     */
    private String id;
    /**
     * 消息类型
     *
     * @return
     */
    private String type;
    /**
     * 操作 1.发布 2.删除
     *
     * @return
     */
    private String operation;
    /**
     * 信息发送时间
     *
     * @return
     */
    private String time;
    /**
     * 版本号
     *
     * @return
     */
    private String version;

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
