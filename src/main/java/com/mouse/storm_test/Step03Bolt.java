package com.mouse.storm_test;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class Step03Bolt extends BaseRichBolt {

	private static final long serialVersionUID = 1L;

	Map stormConf;
	TopologyContext context;
	private OutputCollector outputCollector;

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {

	}

	@Override
	public Map<String, Object> getComponentConfiguration() {

		return null;
	}

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector outputCollector) {
		this.stormConf = stormConf;
		this.context = context;
		this.outputCollector = outputCollector;
	}

	@Override
	public void execute(Tuple input) {
		Map<String,String> res=new HashMap<>();
		String transId=(String) input.getValue(0);
		KafkaMsg msg=(KafkaMsg) input.getValue(1);
		if(msg.getOperation().equals("1")){
			res.put("mongo","true");
		}else if(msg.getOperation().equals("2")){
			res.put("es","true");
		}

//		String sentence = input.getStringByField("sentence03");
//		System.out.println("Step03Bolt ====> " + sentence);
//		try {
//			Thread.sleep(1000);
//			//this.outputCollector.fail(input);
//			//this.outputCollector.ack(input);
//			throw new NullPointerException();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public void cleanup() {

	}

}
