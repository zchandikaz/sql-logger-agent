package info.chandika.sql.logger.agent.instrumentation;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author : chandika
 * @since : 2023-08-06(Sun) 12:30
 **/
public class ThreadLocalFlags {
    public static class ToggleBool {
        private boolean value;

        public ToggleBool() {
        }

        public ToggleBool(boolean value) {
            this.value = value;
        }

        public boolean get() {
            return value;
        }

        public void set(boolean value) {
            this.value = value;
        }

        public void toggle() {
            this.value = !value;
        }

        public boolean getAndToggle() {
            boolean curValue = get();
            toggle();
            return curValue;
        }

        public boolean toggleAndGet() {
            toggle();
            return get();
        }
    }

    public static ThreadLocal<ToggleBool> isFirstCall = ThreadLocal.withInitial(() -> new ToggleBool(true));


}
