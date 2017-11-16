package test.by.teplouhova.chef.factory;

import by.teplouhova.chef.builder.AbstractVegetablesBuilder;
import by.teplouhova.chef.builder.impl.VegetablesDOMBuilder;
import by.teplouhova.chef.builder.impl.VegetablesSAXBuilder;
import by.teplouhova.chef.builder.impl.VegetablesSTAXBuilder;
import by.teplouhova.chef.builder.impl.VegetablesUnMarshBuilder;
import by.teplouhova.chef.factory.VegetablesBuilderFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class VegetablesBuilderFactoryTest {
    private AbstractVegetablesBuilder builder;

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createVegetablesBuilderTestException() {
        builder = VegetablesBuilderFactory.createVegetableBuilder("wrong");
    }

    @Test
    public void createVegetablesSAXBuilderTest() {
        builder = VegetablesBuilderFactory.createVegetableBuilder("sax");
        assertTrue(builder instanceof VegetablesSAXBuilder);
    }

    @Test
    public void createVegetablesSTAXBuilderTest() {
        builder = VegetablesBuilderFactory.createVegetableBuilder("stax");
        assertTrue(builder instanceof VegetablesSTAXBuilder);
    }

    @Test
    public void createVegetablesDOMBuilderTest() {
        builder = VegetablesBuilderFactory.createVegetableBuilder("dom");
        assertTrue(builder instanceof VegetablesDOMBuilder);
    }

    @Test
    public void createVegetablesUnMarshallerBuilderTest() {
        builder = VegetablesBuilderFactory.createVegetableBuilder("unmarshaller");
        assertTrue(builder instanceof VegetablesUnMarshBuilder);
    }
}
