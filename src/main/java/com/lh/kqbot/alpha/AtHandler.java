package com.lh.kqbot.alpha;

import com.lh.kqbot.alpha.utils.ChatBotForQingYunKeUtil;
import com.lh.kqbot.alpha.utils.OffDutyUtil;
import com.lh.kqbot.alpha.utils.UTCFormartUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther: loneyfall
 * @date: 2019/8/19
 * @description: @人的消息处理回复类
 */
public class AtHandler {

    public static String commandHandle(String command, Date time) {
        if ("下班".equals(command)) {
            return OffDutyUtil.getOffDutyTime(time);
        } else if ("我想下班".equals(command)) {
            return OffDutyUtil.getOffDutyTime(time);
        } else if ("傻逼".equals(command)) {
            return "唐家七傻逼!";
        } else if (command.startsWith("utc")) {
            return UTCFormartUtil.formartUTC(command.replace("utc", "").trim());
        }
        String resp = ChatBotForQingYunKeUtil.sendTalk(command);
        if (null != resp && !"".equals(resp)) {
            return resp;
        }
        return "对不起，稽器人目前智力处于0岁水平，还不能回答你的这个问题";
    }
}
