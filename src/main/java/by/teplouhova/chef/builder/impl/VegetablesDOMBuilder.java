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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class VegetablesDOMBuilder extends AbstractVegetablesBuilder {

    private static final Logger LOGGER = LogManager.getLogger();

    private Vegetable vegetable;
    private DocumentBuilder documentBuilder;

    public VegetablesDOMBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.log(Level.ERROR, "Error parser configuration  " + e);
        }
    }

    public void buildSalad(String fileName) {
        Document doc;
        try {
            doc = documentBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList fruitVegetableList = root.getElementsByTagName(SaladEnum.FRUIT_VEGETABLE.getValue());
            NodeList leafyVegetableList = root.getElementsByTagName(SaladEnum.LEAFY_VEGETABLE.getValue());
            for (int index = 0; index < fruitVegetableList.getLength(); index++) {
                Element fruitElement = (Element) fruitVegetableList.item(index);
                Vegetable fruit = buildVegetable(fruitElement);
                salad.add(fruit);
            }
            for (int index = 0; index < leafyVegetableList.getLength(); index++) {
                Element fruitElement = (Element) leafyVegetableList.item(index);
                Vegetable leafy = buildVegetable(fruitElement);
                salad.add(leafy);
            }
        } catch (IOException e) {
            LOGGER.log(Level.FATAL, "File is not found or file error" + fileName + " " + e);
            throw new RuntimeException("File is not found or file error " + fileName + " " + e);
        } catch (SAXException e) {
            LOGGER.log(Level.ERROR, "Error DOM-parser " + e);
        }
    }

    private Vegetable buildVegetable(Element element) {
        if (element.getTagName().equals(SaladEnum.FRUIT_VEGETABLE.getValue())) {
            vegetable = new FruitVegetable();
        }
        if (element.getTagName().equals(SaladEnum.LEAFY_VEGETABLE.getValue())) {
            vegetable = new LeafyVegetable();
        }
        if (element.hasAttribute(SaladEnum.VEGETABLE_ID.getValue())) {
            vegetable.setVegetableId(element.getAttribute(SaladEnum.VEGETABLE_ID.getValue()));
        }
        if (element.hasAttribute(SaladEnum.VEGETABLE_NAME.getValue())) {
            vegetable.setVegetableName(element.getAttribute(SaladEnum.VEGETABLE_NAME.getValue()));
        }
        if (element.hasAttribute(SaladEnum.WAY_COOKING.getValue())) {
            String wayCooking = element.getAttribute(SaladEnum.WAY_COOKING.getValue());

            if (wayCooking != null) {
                vegetable.setWayCooking(WayCooking.valueOf(wayCooking.toUpperCase()));
            } else {
                vegetable.setWayCooking(WayCooking.RAW);
            }

        } else {
            vegetable.setWayCooking(WayCooking.RAW);
        }
        vegetable.setWeight(Integer.parseInt(getElementTextContent(element, SaladEnum.WEIGHT.getValue())));
        vegetable.setCaloricity(Integer.parseInt(getElementTextContent(element, SaladEnum.CALORICITY.getValue())));
        vegetable.setCuttingStyle(CuttingStyle.
                valueOf(getElementTextContent(element, SaladEnum.CUTTING_STYLE.getValue()).toUpperCase()));

        if (vegetable instanceof LeafyVegetable) {
            ((LeafyVegetable) vegetable).setUsingLeaf(Boolean.
                    valueOf(getElementTextContent(element, SaladEnum.USING_LEAF.getValue())));
            ((LeafyVegetable) vegetable).setUsingStalk(Boolean.
                    valueOf(getElementTextContent(element, SaladEnum.USING_STALK.getValue())));
        }
        if (vegetable instanceof FruitVegetable) {
            ((FruitVegetable) vegetable).setUsingSeeks(Boolean.
                    valueOf(getElementTextContent(element, SaladEnum.USING_SEEKS.getValue())));
        }
        vegetable.setComposition(getComposition(element));
        return vegetable;
    }

    private Vegetable.Composition getComposition(Element element) {
        Vegetable.Composition composition = new Vegetable.Composition();
        Element compositionElement = (Element) element.
                getElementsByTagName(SaladEnum.COMPOSITION.getValue()).item(0);
        composition.setCarbohydrate(Double.
                valueOf(getElementTextContent(compositionElement, SaladEnum.CARBOHYDRATE.getValue())));
        composition.setProtein(Double.
                valueOf(getElementTextContent(compositionElement, SaladEnum.PROTEIN.getValue())));
        composition.setFat(Double.
                valueOf(getElementTextContent(compositionElement, SaladEnum.FAT.getValue())));
        return composition;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
