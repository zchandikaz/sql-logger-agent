package info.chandika.sql.logger.agent.instrumentation.transformer;

import java.security.ProtectionDomain;

/**
 * @author : chandika
 * @since : 2024-03-27(Wed) 20:21
 **/
public interface IDriverTransformer {
    byte[] apply(Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer);
}
