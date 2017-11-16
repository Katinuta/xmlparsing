package by.teplouhova.chef.sax;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class VegetableErrorHandler implements ErrorHandler {

    private static final Logger LOGGER = LogManager.getLogger();

    public VegetableErrorHandler() {
    }

    private String getParserExceptionInfo(SAXParseException exception) {
        String systemId = exception.getSystemId();
        if (systemId == null) {
            systemId = null;
        }
        String info = "URI=" + systemId + exception.getLineNumber() + ":" + exception.getMessage();
        return info;
    }

    @Override
    public void warning(SAXParseException exception) {
        LOGGER.log(Level.WARN, "Warning:" + getParserExceptionInfo(exception));

    }

    @Override
    public void error(SAXParseException exception) {
        LOGGER.log(Level.ERROR, "Error:" + getParserExceptionInfo(exception));
    }

    @Override
    public void fatalError(SAXParseException exception) {
        LOGGER.log(Level.FATAL, "Fatal Error:" + getParserExceptionInfo(exception));
        throw new RuntimeException("Fatal Error:" + getParserExceptionInfo(exception));
    }
}
