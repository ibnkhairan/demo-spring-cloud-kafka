package com.ael.springcloud.streams.kafka.web;

import com.ael.springcloud.streams.kafka.entities.PageEvent;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
public class PageEventRestController {

    private StreamBridge streamBridge;

    public PageEventRestController(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @GetMapping("publish/{topic}/{name}")
    public PageEvent publish(@PathVariable String topic, @PathVariable String name){
        PageEvent pageEvent=new PageEvent();
        pageEvent.setName(name);
        pageEvent.setDate(new Date());
        pageEvent.setDuration(new Random().nextInt(1000));
        pageEvent.setUser(Math.random()>0.5?"U1":"U2");
        streamBridge.send(topic,pageEvent);
        return pageEvent;
    }
}
