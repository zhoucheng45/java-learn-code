package cn.com.benny.learn.controller;

import cn.com.benny.learn.model.Order;
import cn.com.benny.learn.utils.OrderUtil;
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
                Order order = OrderUtil.generatorOrder();
                kafkaTemplate.send("order", order.getOrderNo(), order.toString());
            }
            return  "发送kafka成功,message="+message;
        } catch (Exception e) {
            return  "发送kafka失败";
        }
    }




}