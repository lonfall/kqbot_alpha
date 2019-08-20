package com.lh.kqbot.alpha.listener;

import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.message.EventDiscussMessage;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import cc.moecraft.icq.event.events.message.EventGroupOrDiscussMessage;
import cc.moecraft.icq.event.events.message.EventPrivateMessage;
import com.lh.kqbot.alpha.AtHandler;

import java.util.Date;

/**
 * @auther: loneyfall
 * @date: 2019/8/19
 * @description: 消息监听
 */
public class MsgListener extends IcqListener {

    /**
     * 消息接收——私聊
     *
     * @param event
     */
    @EventHandler
    public void onPMEvent(EventPrivateMessage event) {
        System.out.println("---------------接收到一条消息");
        System.out.println("sender:" + event.getSender().getId());
        System.out.println("message" + event.getMessage());
        String answer = AtHandler.commandHandle(event.getMessage(), new Date(event.getTime() * 1000));
        event.respond(answer);
    }

    /**
     * 消息接收——群聊
     *
     * @param event
     */
    @EventHandler
    public void onPGEvent(EventGroupMessage event) {
        System.out.println("---------------接收到一条群聊消息");
        System.out.println("sender:" + event.getSender().getId());
        System.out.println("message" + event.getMessage());
        commandAt(event);
    }

    /**
     * 消息接收——讨论组
     *
     * @param event
     */
    @EventHandler
    public void onPDEvent(EventDiscussMessage event) {
        System.out.println("---------------接收到一条讨论组消息");
        System.out.println("sender:" + event.getSender().getId());
        System.out.println("message:" + event.getMessage());
        commandAt(event);
    }

    private void commandAt(EventGroupOrDiscussMessage event) {
        String message = event.getMessage();
        Long selfId = event.getSelfId();
        if (message.indexOf("[CQ:at,qq=" + selfId + "]") != -1) {
            String command = message.replace("[CQ:at,qq=" + selfId + "]", "").trim();
            System.out.println("指令：" + command);
            String answer = AtHandler.commandHandle(command, new Date(event.getTime() * 1000));
            event.respond(answer);
        }
    }
}
