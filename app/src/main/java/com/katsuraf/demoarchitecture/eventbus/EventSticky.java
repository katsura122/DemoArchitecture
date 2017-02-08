package com.katsuraf.demoarchitecture.eventbus;

/**
 * 类描述：
 * 创建人：user
 * 创建时间：2016/8/12 17:48
 * 修改人：user
 * 修改时间：2016/8/12 17:48
 * 修改备注：
 *
 * @Copyright: 2016 Chengdu Economic Daily NNL Inc. All rights reserved.
 */
public class EventSticky {
    private int id;
    private Object data;

    public EventSticky(int eventId, Object data) {
        this.id = eventId;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public Object getData() {
        return data;
    }
}
