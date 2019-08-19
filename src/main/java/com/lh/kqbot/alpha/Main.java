package com.lh.kqbot.alpha;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.PicqConfig;
import com.lh.kqbot.alpha.command.CommandOffDuty;
import com.lh.kqbot.alpha.listener.MsgListener;

/**
 * @auther: loneyfall
 * @date: 2019/8/19
 * @description:
 */
public class Main {

    public static void main(String[] args) {
        // 创建机器人配置 ( 传入Picq端口 )
        PicqConfig config = new PicqConfig(30052);
        // 创建机器人对象 ( 传入机器人配置对象 )
        PicqBotX bot = new PicqBotX(config);
        // 添加一个机器人账户 ( 传入名字, 酷Q URL, 酷Q端口 )
        bot.addAccount("Bot01", "127.0.0.1", 30051);
        // 注册事件监听器, 可以注册多个监听器
        bot.getEventManager().registerListeners(
                new MsgListener());
        // 启用指令管理器
        // 这些字符串是指令前缀, 比如 !help 的前缀就是"!"
        bot.enableCommandManager("bot -", "!", "/", "~");
        // 注册指令, 可以注册多个指令
        bot.getCommandManager().registerCommands(
                new CommandOffDuty());
        // 启动机器人, 不会占用主线程
        bot.startBot();
    }
}
