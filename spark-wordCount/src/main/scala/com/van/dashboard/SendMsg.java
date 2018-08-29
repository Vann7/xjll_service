package com.van.dashboard;

import com.van.dashboard.kafka.KafkaProducerUtil;

import java.util.ArrayList;
import java.util.List;

public class SendMsg {

    private static String url = "ubuntu:9092,ubuntu2:9092";
    private static String msg = "user_id,item_id,cat_id,merchant_id,brand_id,month,day,action,age_range,gender,province\n" +
            "328862,323294,833,2882,2661,08,29,0,0,1,内蒙古\n" +
            "328862,844400,1271,2882,2661,08,29,0,1,1,山西\n" +
            "328862,575153,1271,2882,2661,08,29,0,2,1,山西\n" +
            "328862,996875,1271,2882,2661,08,29,0,1,1,内蒙古\n" +
            "328862,1086186,1271,1253,1049,08,29,0,0,2,浙江\n" +
            "328862,623866,1271,2882,2661,08,29,0,0,2,黑龙江\n" +
            "328862,542871,1467,2882,2661,08,29,0,5,2,四川\n" +
            "328862,536347,1095,883,1647,08,29,0,7,1,吉林";

    public static void send() {
        List<String> list = new ArrayList<String>();

        String[] array = msg.split("\n");
        for(int i=1; i<array.length; i++) {
            String[] arr = array[i].split(",");
            String sex = arr[arr.length - 2];
            String age = arr[arr.length - 3];
            //String province = arr[arr.length-1];
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            KafkaProducerUtil.sendMessage(sex,url,"gender");
            //KafkaProducerUtil.sendMessage(sex,url,"result");
        }
    }

    public static void main(String[] args){
        while (true) {
            send();

        }

    }

}
