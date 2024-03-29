package info.chandika.sql.logger.agent.lang;

/**
 * @author : chandika
 * @since : 2022-09-08(Thu) 17:36
 **/


public final class Throwing {

    private Throwing() {
    }

    /**
     * The compiler sees the signature with the throws T inferred to a RuntimeException type, so it
     * allows the unchecked exception to propagate.
     * <p>
     * http://www.baeldung.com/java-sneaky-throws
     */
    @SuppressWarnings("unchecked")
    public static <E extends Throwable> void sneakyThrow(Throwable ex) throws E {
        throw (E) ex;
    }

}
