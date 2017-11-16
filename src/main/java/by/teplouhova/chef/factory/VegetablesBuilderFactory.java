package by.teplouhova.chef.factory;

import by.teplouhova.chef.builder.*;
import by.teplouhova.chef.builder.impl.VegetablesDOMBuilder;
import by.teplouhova.chef.builder.impl.VegetablesSAXBuilder;
import by.teplouhova.chef.builder.impl.VegetablesSTAXBuilder;
import by.teplouhova.chef.builder.impl.VegetablesUnMarshBuilder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VegetablesBuilderFactory {

    private static final Logger LOGGER = LogManager.getLogger();

    private enum TypeParser {
        SAX, STAX, DOM, UNMARSHALLER
    }

    public static AbstractVegetablesBuilder createVegetableBuilder(String typeParser) {

        AbstractVegetablesBuilder builder = null;
        try {
            TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
            switch (type) {
                case DOM:
                    builder = new VegetablesDOMBuilder();
                    break;
                case SAX:
                    builder = new VegetablesSAXBuilder();
                    break;
                case STAX:
                    builder = new VegetablesSTAXBuilder();
                    break;
                case UNMARSHALLER:
                    builder = new VegetablesUnMarshBuilder();
                    break;
            }
        } catch (EnumConstantNotPresentException e) {
            LOGGER.log(Level.FATAL, "Parser can not be created " + typeParser + e);
            throw new RuntimeException("Parser can not be created " + e);
        }
        return builder;
    }
}
