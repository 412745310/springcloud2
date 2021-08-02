package com.chelsea.springcloud2.event.event;

import org.springframework.context.ApplicationEvent;

/**
 * 装载消息对象
 * 
 * @author shevchenko
 *
 */
public class EventDemo extends ApplicationEvent {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String message;

    public EventDemo(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
