package com.lh.kqbot.alpha.command;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;
import com.lh.kqbot.alpha.utils.OffDutyUtil;

import java.util.ArrayList;
import java.util.Date;

/**
 * @auther: loneyfall
 * @date: 2019/8/19
 * @description: 下班指令
 */
public class CommandOffDuty implements EverywhereCommand {

    public CommandProperties properties() {
        // 这个括号里填指令名和其他名称, 指令名必须至少有一个
        return new CommandProperties("fuck-job", "我想下班", "下班");
    }

    public String run(EventMessage event, User sender, String command, ArrayList<String> args) {
        return OffDutyUtil.getOffDutyTime(new Date());
    }

}
