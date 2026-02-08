package spark.exceptions;

/**
 * Custom exception class for Spark application.
 * Used to handle specific error scenarios within the application.
 */
public class SparkException extends Exception {
    /**
     * Constructs the SparkException class. (Constructor)
     *
     * @param message The error message associated with the exception.
     */
    public SparkException(String message) {
        super(message);
    }
}
