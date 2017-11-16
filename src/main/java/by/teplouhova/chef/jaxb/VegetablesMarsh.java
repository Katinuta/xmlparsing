package by.teplouhova.chef.jaxb;

import by.teplouhova.chef.entity.Salad;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileOutputStream;

public class VegetablesMarsh {

    private static final Logger LOGGER = LogManager.getLogger();
    private Marshaller marshaller;

    public VegetablesMarsh() {
        try {
            JAXBContext context = JAXBContext.newInstance(Salad.class);
            marshaller = context.createMarshaller();
        } catch (JAXBException e) {
            LOGGER.log(Level.ERROR, "JAXB - context is incorrect" + e);
        }

    }

    public void buildVegetablesXML(Salad salad, FileOutputStream outputStream) {
        try {
            marshaller.marshal(salad, outputStream);
        } catch (JAXBException e) {
            LOGGER.log(Level.ERROR, "JAXB  marshaller error" + e);
        }

    }
}
