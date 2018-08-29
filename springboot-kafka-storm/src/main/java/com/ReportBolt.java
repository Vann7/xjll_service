package com;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 上报bolt
 */
public class ReportBolt extends BaseRichBolt {

    private HashMap<String, Long> counts = null;

    @Override
    public void prepare(Map map, TopologyContext context, OutputCollector collector) {
            this.counts = new HashMap<>();
    }

    @Override
    public void execute(Tuple tuple) {
        String word = tuple.getStringByField("word");
        Long count = tuple.getLongByField("count");
        this.counts.put(word, count);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }

    @Override
    public void cleanup() {
        System.out.println("---FINAL COUNTS---");
        List<String> keys = new ArrayList<>();
        keys.addAll(this.counts.keySet());
        for(String key : keys) {
            System.out.println(key + ": " + this.counts.get(key));
        }
        System.out.println("-------------");
    }


}
