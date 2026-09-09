package org.qa.utilities;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerManager {

    // This method return a Logger instance for the provided class
    public static Logger getLogger(Class<?> cls) {
        return LogManager.getLogger();
    }


}
