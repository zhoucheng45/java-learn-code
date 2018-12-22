package cn.com.benny.learn.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/kafka")
public class CollectController {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String sendKafka(@RequestParam("message") String message) {
        try {
            JSONObject jsonObject = new JSONObject();


            for (int i=0;i<1000*1000;i++){
                kafkaTemplate.send("test", "key", message);
            }
            return  "发送kafka成功";
        } catch (Exception e) {
            return  "发送kafka失败";
        }
    }

}