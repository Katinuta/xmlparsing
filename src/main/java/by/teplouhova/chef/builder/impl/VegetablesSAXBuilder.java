package by.teplouhova.chef.builder.impl;

import by.teplouhova.chef.sax.VegetableErrorHandler;
import by.teplouhova.chef.sax.VegetableHandler;
import by.teplouhova.chef.builder.AbstractVegetablesBuilder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class VegetablesSAXBuilder extends AbstractVegetablesBuilder {

    private static final Logger LOGGER = LogManager.getLogger();
    private VegetableHandler vegetableHandler;
    private VegetableErrorHandler errorHandler;
    private XMLReader reader;

    public VegetablesSAXBuilder() {
        vegetableHandler = new VegetableHandler();
        errorHandler = new VegetableErrorHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(vegetableHandler);
            reader.setErrorHandler(errorHandler);
        } catch (SAXException e) {
            LOGGER.log(Level.ERROR, "Error SAX-parser " + e);
        }
    }

    public void buildSalad(String fileName) {
        try {
            reader.parse(fileName);
        } catch (IOException e) {
            LOGGER.log(Level.FATAL, "File is not found or file error" + fileName + " " + e);
            throw new RuntimeException("File is not found or file error " + fileName + " " + e);
        } catch (SAXException e) {
            LOGGER.log(Level.ERROR, "Error SAX-parser " + e);
        }
        salad = vegetableHandler.getSalad();
    }
}
