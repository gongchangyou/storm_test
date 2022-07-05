package com.mouse.storm_test;

import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.IBasicBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

@SuppressWarnings("rawtypes")
public class Step02Bolt implements IBasicBolt {

	private static final long serialVersionUID = 1L;

	Map stormConf;
	TopologyContext context;

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declareStream("Step03Bolt",new Fields("transId","msg"));
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {

		return null;
	}

	@Override
	public void prepare(Map stormConf, TopologyContext context) {
		this.stormConf = stormConf;
		this.context = context;
	}

	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		KafkaMsg msg=(KafkaMsg)input.getValue(0);
		msg.setOperation("2");
		String transId=msg.getTransId();
//		String sentence = input.getStringByField("sentence");
		collector.emit("es",new Values(transId,msg));
	}

	@Override
	public void cleanup() {

	}

}
