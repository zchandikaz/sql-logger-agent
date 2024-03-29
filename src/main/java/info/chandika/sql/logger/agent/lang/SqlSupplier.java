package info.chandika.sql.logger.agent.lang;

import java.sql.SQLException;

/**
 * @author : chandika
 * @since : 2022-09-24(Sat) 21:07
 **/
@FunctionalInterface
public interface SqlSupplier<T> {

    /**
     * Gets a result.
     *
     * @return a result
     */
    T get() throws SQLException;
}
