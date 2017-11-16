package by.teplouhova.chef.builder.impl;

import by.teplouhova.chef.builder.AbstractVegetablesBuilder;
import by.teplouhova.chef.entity.ObjectFactory;
import by.teplouhova.chef.entity.Salad;
import by.teplouhova.chef.entity.Vegetable;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.Iterator;

public class VegetablesUnMarshBuilder extends AbstractVegetablesBuilder {

    private static final Logger LOGGER = LogManager.getLogger();
    private Unmarshaller unmarshaller;

    public VegetablesUnMarshBuilder() {
        JAXBContext jc;
        try {
            jc = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
            unmarshaller = jc.createUnmarshaller();
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            String schemaName = "data/vegetable.xsd";
            File schemaLocation = new File(schemaName);
            Schema schema = factory.newSchema(schemaLocation);
            unmarshaller.setSchema(schema);
        } catch (JAXBException e) {
            LOGGER.log(Level.ERROR, "JAXB - context is incorrect" + e);
        } catch (SAXException e) {
            LOGGER.log(Level.ERROR, "JAXB  unmarshaller error" + e);
        }
    }

    @Override
    public void buildSalad(String filename) {
        try {
            Salad vegetables = ((Salad) unmarshaller.unmarshal(new File(filename)));
            Iterator<JAXBElement<? extends Vegetable>> iterator = vegetables.getIterator();
            while (iterator.hasNext()) {
                salad.add(iterator.next().getValue());
            }
        } catch (JAXBException e) {
            LOGGER.log(Level.ERROR, "JAXB  unmarshaller error " + e);
        }
    }
}
