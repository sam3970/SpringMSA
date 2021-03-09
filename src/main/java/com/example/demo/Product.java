package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostPersist;

@Entity
public class Product {
    @Id
    @GeneratedValue
    Long id;
    String name;
    int stock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @PostPersist
        //database에 commit 된 다음 해당
        //ProductChanged 이벤트를 객체 생성하고, 카프카에 Publish..
        public void eventPublish(){
            ProductChanged productChanged = new ProductChanged();
            productChanged.setProductId(this.getId());
            productChanged.setProductName(this.getName());
            productChanged.setProductStock(this.getStock());
            ObjectMapper objectMapper = new ObjectMapper();
            String json = null;

            try {
                json = objectMapper.writeValueAsString(productChanged);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("JSON format exception", e);
            }

        Processor processor = DemoApplication.applicationContext.getBean(Processor.class);
        MessageChannel outputChannel = processor.output();

        outputChannel.send(MessageBuilder
                .withPayload(json)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
            System.out.println(json);
        }

    //@PostUpdate
        //Patch로 수정된 이후에 method 블록 실행.

    //@PostRemove
        //삭제되고 난 다음에 해당 method 블록 실행.

    //@PrePersist
        //POST 요청이 들어왔을 때 처리 되기 전 실행.

    //@PreUpdate
        //업데이트 전 실행.

    //@PreRemove
        //삭제 전 실행
}
