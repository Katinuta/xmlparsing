package by.teplouhova.chef.builder.impl;

import by.teplouhova.chef.builder.AbstractVegetablesBuilder;
import by.teplouhova.chef.builder.SaladEnum;
import by.teplouhova.chef.entity.CuttingStyle;
import by.teplouhova.chef.entity.Vegetable;
import by.teplouhova.chef.entity.WayCooking;
import by.teplouhova.chef.entity.FruitVegetable;
import by.teplouhova.chef.entity.LeafyVegetable;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class VegetablesSTAXBuilder extends AbstractVegetablesBuilder {
    private static final Logger LOGGER = LogManager.getLogger();
    private Vegetable vegetable;
    private XMLInputFactory xmlInputFactory;

    public VegetablesSTAXBuilder() {
        xmlInputFactory = XMLInputFactory.newInstance();
    }

    public void buildSalad(String fileName) {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(new File(fileName))) {
            reader = xmlInputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();

                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (SaladEnum.valueOf(name.toUpperCase().replace("-", "_")) == SaladEnum.LEAFY_VEGETABLE) {
                        vegetable = new LeafyVegetable();
                        vegetable = buildVegetable(reader);
                        salad.add(vegetable);
                    }
                    if (SaladEnum.valueOf(name.toUpperCase().replace("-", "_")) == SaladEnum.FRUIT_VEGETABLE) {
                        vegetable = new FruitVegetable();
                        vegetable = buildVegetable(reader);
                        salad.add(vegetable);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.FATAL, "File is not found : " + fileName + " " + e);
            throw new RuntimeException("File is not found : " + fileName + " " + e);

        } catch (XMLStreamException e) {
            LOGGER.log(Level.ERROR, "Error STAX-parser " + e);
        } catch (IOException e) {
            LOGGER.log(Level.FATAL, "Error file" + fileName + e);
            throw new RuntimeException("Error file" + fileName + e);
        }
    }

    private Vegetable buildVegetable(XMLStreamReader reader) {
        String name;
        if (reader.getAttributeCount() != 0) {
            int countAttribute = reader.getAttributeCount();
            for (int index = 0; index < countAttribute; index++) {
                name = reader.getAttributeLocalName(index);
                switch (SaladEnum.valueOf(name.toUpperCase().replace("-", "_"))) {
                    case VEGETABLE_ID:
                        vegetable.setVegetableId(reader.getAttributeValue(null, SaladEnum.VEGETABLE_ID.getValue()));
                        break;
                    case VEGETABLE_NAME:
                        vegetable.setVegetableName(reader.getAttributeValue(null, SaladEnum.VEGETABLE_NAME.getValue()));
                        break;
                    case WAY_COOKING:
                        vegetable.setWayCooking(
                                WayCooking.valueOf(reader.getAttributeValue(null, SaladEnum.WAY_COOKING.getValue()).toUpperCase()));
                        break;
                }
            }
        }
        try {
            while (reader.hasNext()) {
                int type = reader.next();

                switch (type) {

                    case XMLStreamConstants.START_ELEMENT: {
                        name = reader.getLocalName();

                        switch (SaladEnum.valueOf(name.toUpperCase().replace("-", "_"))) {
                            case WEIGHT:
                                vegetable.setWeight(Integer.parseInt(getXMLTxt(reader)));
                                break;
                            case CALORICITY:
                                vegetable.setCaloricity(Integer.parseInt(getXMLTxt(reader)));
                                break;
                            case CUTTING_STYLE:
                                vegetable.setCuttingStyle(CuttingStyle.valueOf(getXMLTxt(reader).toUpperCase()));
                                break;
                            case COMPOSITION:
                                vegetable.setComposition(buildComposition(reader));
                                break;
                            case USING_SEEKS: {
                                if (vegetable instanceof FruitVegetable) {

                                    ((FruitVegetable) vegetable).setUsingSeeks(Boolean.valueOf(getXMLTxt(reader)));
                                }
                                break;
                            }
                            case USING_LEAF: {
                                if (vegetable instanceof LeafyVegetable) {
                                    ((LeafyVegetable) vegetable).setUsingLeaf(Boolean.valueOf(getXMLTxt(reader)));
                                }
                                break;
                            }
                            case USING_STALK: {
                                if (vegetable instanceof LeafyVegetable) {
                                    ((LeafyVegetable) vegetable).setUsingStalk(Boolean.valueOf(getXMLTxt(reader)));
                                }
                                break;
                            }
                        }
                        break;
                    }
                    case XMLStreamConstants.END_ELEMENT: {

                        name = reader.getLocalName().replace("-", "_").toUpperCase();
                        if (SaladEnum.valueOf(name) == SaladEnum.LEAFY_VEGETABLE || SaladEnum.valueOf(name) == SaladEnum.FRUIT_VEGETABLE) {
                            if (vegetable.getWayCooking() == null) {
                                vegetable.setWayCooking(WayCooking.RAW);
                            }
                            return vegetable;
                        }
                        break;
                    }
                }
            }

        } catch (XMLStreamException e) {
            LOGGER.log(Level.ERROR, "Error STAX-parser " + e);
        }

        return vegetable;
    }

    private String getXMLTxt(XMLStreamReader reader) throws XMLStreamException {
        String text = null;

        if (reader.hasNext()) {
            reader.next();
            if (!reader.isEndElement()) {
                text = reader.getText();
            }

        }

        return text;
    }

    private Vegetable.Composition buildComposition(XMLStreamReader reader) throws XMLStreamException {
        Vegetable.Composition composition = new Vegetable.Composition();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT: {
                    name = reader.getLocalName();
                    switch (SaladEnum.valueOf(name.replace("-", "_").toUpperCase())) {
                        case FAT:
                            composition.setFat(Double.parseDouble(getXMLTxt(reader)));
                            break;
                        case CARBOHYDRATE:
                            composition.setCarbohydrate(Double.parseDouble(getXMLTxt(reader)));
                            break;
                        case PROTEIN:
                            composition.setProtein(Double.parseDouble(getXMLTxt(reader)));
                            break;
                    }
                    break;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    name = reader.getLocalName();
                    if (SaladEnum.valueOf(name.replace("-", "_").toUpperCase()) == SaladEnum.COMPOSITION) {
                        return composition;
                    }
                    break;
                }
            }
        }
        throw new XMLStreamException();
    }
}
