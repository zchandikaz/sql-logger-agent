package info.chandika.sql.logger.agent.instrumentation;

import java.lang.instrument.Instrumentation;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author : chandika
 * @since : 2023-07-31(Mon) 23:46
 *
 * Ref: https://www.baeldung.com/java-instrumentation
 **/
public class DriverAgent {
    private static final Logger LOGGER = Logger.getLogger(DriverAgent.class.getName());

    // -javaagent:jar
    public static void premain(
            String agentArgs, Instrumentation inst) {

        LOGGER.info("[Agent] In premain method");
        transformClass(inst);
    }

    // attach while running
    public static void agentmain(
            String agentArgs, Instrumentation inst) {

        LOGGER.info("[Agent] In agentmain method");
//        transformClass(inst);
    }

 private static void transformClass(Instrumentation instrumentation) {
        String className = "org.postgresql.Driver";

        Class<?> targetCls = null;
        ClassLoader targetClassLoader = null;
        // see if we can get the class using forName
        try {
            targetCls = Class.forName(className);
            targetClassLoader = targetCls.getClassLoader();
            transform(targetCls, targetClassLoader, instrumentation);
            return;
        } catch (Exception ex) {
//            LOGGER.log(Level.SEVERE, "Class not found with Class.forName", ex);
        }
        // otherwise iterate all loaded classes and find what we want
        for (Class<?> clazz : instrumentation.getAllLoadedClasses()) {
//            System.out.println(clazz.getName());
            if (clazz.getName().equals(className)) {
                targetCls = clazz;
                targetClassLoader = targetCls.getClassLoader();
                transform(targetCls, targetClassLoader, instrumentation);
                return;
            }
        }
        throw new RuntimeException(
                "Failed to find class [" + className + "]");
    }


    private static void transform(Class<?> clazz, ClassLoader classLoader, Instrumentation instrumentation) {
        LOGGER.log(Level.INFO, "Transforming: {0}", new Object[]{ clazz.getName() });
        ExDriverTransformer dt = new ExDriverTransformer(clazz.getName(), classLoader);
        instrumentation.addTransformer(dt, true);
        try {
            instrumentation.retransformClasses(clazz);
        } catch (Exception ex) {
            throw new RuntimeException(
                    "Transform failed for: [" + clazz.getName() + "]", ex);
        }
    }
}
