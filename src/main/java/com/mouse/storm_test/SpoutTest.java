package com.mouse.storm_test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.storm.shade.org.eclipse.jetty.util.ajax.JSON;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class SpoutTest extends BaseRichSpout {

	private static final long serialVersionUID = 1L;

	// key:messageId,Data
	private HashMap<String, String> waitAck = new HashMap<String, String>();

	private SpoutOutputCollector collector;
	/*id : 7500004723
	 * type : 1101
	 * operation : 1
	 * data : {"assetId":"7500004723","identity":"0"}
	 * time : 2020-06-02 14:05:52
	 * version : 1591077952492
	 * transId : 1101-b10057a7-0cff-4aef-9b84-283501d36932
	 * method : KAFKA*
	 *
	 * id : 7500004723
	 * type : 1101
	 * operation : 1
	 * data : {"shortProgramId":"7500004723","longProgramIds":["7500004725","7500004726"]}
	 * time : 2020-06-02 14:05:52
	 * version : 1591077952492
	 * transId : 1101-b10057a7-0cff-4aef-9b84-283501d36932
	 * method : KAFKA
	 *
	 *
	 * contId : 10001
	 * poolId : 20001
	 * images : [{"label":"标签名称","timeStamp":3600,"pics":[{"width":400,"height":200,"type":"big","path":"/nas/store/zhengshi/clip/0000/111/221/xancljhuwapi300.jpg"},{"width":400,"height":200,"type":"small","path":"/nas/store/zhengshi/clip/0000/111/221/xancljhuwapi300.jpg"}]}]
	 */
	private static KafkaMsg kafkaMsg = new KafkaMsg();
	static {
		/*HostAtlas.ImagesBean.PicsBean big = new HostAtlas.ImagesBean.PicsBean(400, 200, "big", "/nas/store/zhengshi/clip/0000/111/221/xancljhuwapi300.jpg");
		HostAtlas.ImagesBean.PicsBean small = new HostAtlas.ImagesBean.PicsBean(400, 200, "small", "/nas/store/zhengshi/clip/0000/111/221/xancljhuwapi300.jpg");
		ArrayList<HostAtlas.ImagesBean.PicsBean> picsBeans = new ArrayList<>();
		picsBeans.add(big);
		picsBeans.add(small);
		HostAtlas.ImagesBean imgs = new HostAtlas.ImagesBean("标签名称", 3600, picsBeans);
		ArrayList<HostAtlas.ImagesBean> imagesBeans = new ArrayList<>();
		imagesBeans.add(imgs);
		HostAtlas hostAtlas = new HostAtlas("10001", "2001", imagesBeans,1691077952492L);
		String s = JSON.toJSONString(hostAtlas);*/
		kafkaMsg.setId("7500004723");
		kafkaMsg.setType("1101");
		kafkaMsg.setOperation("2");
		kafkaMsg.setData("{\"assetId\":\"7500004723\",\"identity\":\"1\"}");
		kafkaMsg.setTime("2020-06-02 14:05:52");
		kafkaMsg.setVersion("1991077952492");
		kafkaMsg.setTransId("1101-b10057a7-0cff-4aef-9b84-283501d36932");
		kafkaMsg.setMethod("KAFKA");
	}
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void nextTuple() {

		/*String sentence = "https://v.miguvideo.com";
		String messageId = UUID.randomUUID().toString().replaceAll("-", "");*/
		//waitAck.put(kafkaMsg.getId(), JSON.toJSONString(kafkaMsg));
		try {
			collector.emit(new Values(new ObjectMapper().writeValueAsString(kafkaMsg)), kafkaMsg.getId()); // 指定messageId，开启ackfail机制
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}


	}

	@Override
	public void ack(Object msgId) {
		/*System.out.println("消息处理成功:" + msgId);
		waitAck.remove(msgId);*/
	}

	@Override
	public void fail(Object msgId) {
		/*System.out.println("消息处理失败:" + msgId);
		collector.emit(new Values(waitAck.get(msgId)), msgId); // 重发如果不开启ackfail机制，那么spout的map对象中的该数据不会被删除的。*/
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("value"));
	}

}
