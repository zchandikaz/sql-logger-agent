package info.chandika.sql.logger.agent.logger;

import info.chandika.sql.logger.agent.util.Configs;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author : chandika
 * @since : 2022-07-06(Wed) 11:25
 **/


public class ConnectionLogger {
    private static String IGNORE_STACK_PACKAGE = "info.chandika.sql.logger";
    private static final AtomicInteger OPEN_CONNECTION_COUNT = new AtomicInteger(0);
    private static final ThreadLocal<AtomicInteger> OPEN_INHERITED_CONNECTION_COUNT_IN_THREAD = new InheritableThreadLocal<>() {
        @Override
        protected AtomicInteger initialValue() {
            return new AtomicInteger(0);
        }
    };

    public static final Logger LOGGER = Logger.getLogger("ConnectionLogger");

    public static void log(String msg) {
        if (Configs.SOUT_ENABLED) {
            System.out.println("[SQL] " + msg + "\n");
        } else {
            LOGGER.info(msg + "\n");
        }
    }

    public static void logSQL(String msg) {
        msg = msg.replaceAll("\\n", " ").replaceAll("\\r", " ");
        if (Configs.CALLER_ENABLED) {
            msg = getCaller() + " :: " + msg;
        }
        log(msg);
    }

    public static String getCaller() {
        var caller = "N/A";
        var stackArr = Thread.currentThread().getStackTrace();
        for (var i = 1; i < stackArr.length; i++) {
            var stackEl = stackArr[i];
            if (stackEl.getClassName().startsWith(IGNORE_STACK_PACKAGE) || !isCodebasePackage(stackEl.getClassName())) {
                continue;
            }
            caller = stackEl.getClassName() + "." + stackEl.getMethodName() + "(" + stackEl.getFileName() + ":" + stackEl.getLineNumber() + ")";
            break;
        }
        return caller;
    }

    private static boolean isCodebasePackage(String fqcn) {
        for (String codebasePackage : Configs.CODEBASE_PACKAGES) {
            if (fqcn.startsWith(codebasePackage)) {
                return true;
            }
        }
        return false;
    }

    public static void logEvent(ConnectionEvent event) {
        int totalConCount = 0;
        int inheritedThreadConCount = 0;
        if (event == ConnectionEvent.OPEN) {
            totalConCount = OPEN_CONNECTION_COUNT.incrementAndGet();
            inheritedThreadConCount = OPEN_INHERITED_CONNECTION_COUNT_IN_THREAD.get().incrementAndGet();
        } else if (event == ConnectionEvent.CLOSE) {
            totalConCount = OPEN_CONNECTION_COUNT.decrementAndGet();
            inheritedThreadConCount = OPEN_INHERITED_CONNECTION_COUNT_IN_THREAD.get().decrementAndGet();
        }
        LOGGER.log(Level.INFO, "[{0}] Connection-Event: {1} | Caller: {2} | Cons: [all={3}, thread={4}]", new Object[]{Thread.currentThread().getId(), event.name(), getCaller(), totalConCount, inheritedThreadConCount});
    }

    public enum ConnectionEvent {
        OPEN, CLOSE
    }
}
