package example.utils;

import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;

import java.util.Collection;

/**
 * Created by zhh on 2018/6/6 0006.
 */
public class DwrPushUtils {

    private DwrPushUtils() {}

    public static void send(String msg) {
        WebContext webContext = WebContextFactory.get();
        Collection<ScriptSession> allScriptSessions = webContext.getAllScriptSessions();
        ScriptBuffer sb = new ScriptBuffer();
        // 调用页面上的回调方法
        sb.appendScript("callback(")
                .appendScript(msg)
                .appendScript(")");
        // 向客户端推送消息
        Util util = new Util(allScriptSessions);
        util.addScript(sb);
    }
}
