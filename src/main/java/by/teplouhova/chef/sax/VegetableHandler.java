package by.teplouhova.chef.sax;

import by.teplouhova.chef.builder.SaladEnum;
import by.teplouhova.chef.entity.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashSet;
import java.util.Set;

public class VegetableHandler extends DefaultHandler {
    private Vegetable currentVegetable;
    private Set<Vegetable> salad;
    private SaladEnum currentEnum;

    public VegetableHandler() {
        salad = new HashSet<>();
    }

    public Set<Vegetable> getSalad() {
        return salad;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if ("fruit-vegetable".equals(localName)) {
            currentVegetable = new FruitVegetable();
        } else if ("leafy-vegetable".equals(localName)) {
            currentVegetable = new LeafyVegetable();
        } else {
            currentEnum = SaladEnum.valueOf(localName.toUpperCase().replace("-", "_"));
        }
        if (attrs != null) {
            for (int index = 0; index < attrs.getLength(); index++) {
                if ("vegetable-id".equals(attrs.getLocalName(index))) {
                    currentVegetable.setVegetableId(attrs.getValue(index));
                }
                if ("vegetable-name".equals(attrs.getLocalName(index))) {
                    currentVegetable.setVegetableName(attrs.getValue(index));
                }
                if ("way-cooking".equals(attrs.getLocalName(index))) {
                    if (attrs.getValue(index) != null) {
                        currentVegetable.setWayCooking(WayCooking.valueOf(attrs.getValue(index).toUpperCase()));
                    }
                }

            }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if ("fruit-vegetable".equals(localName) || "leafy-vegetable".equals(localName)) {
            if (currentVegetable.getWayCooking() == null) {
                currentVegetable.setWayCooking(WayCooking.RAW);
            }
            salad.add(currentVegetable);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case WEIGHT:
                    currentVegetable.setWeight(Integer.parseInt(value));
                    break;
                case CALORICITY:
                    currentVegetable.setCaloricity(Integer.parseInt(value));
                    break;
                case FAT:
                    currentVegetable.getComposition().setFat(Double.parseDouble(value));
                    break;
                case PROTEIN:
                    currentVegetable.getComposition().setProtein(Double.parseDouble(value));
                    break;
                case CARBOHYDRATE:
                    currentVegetable.getComposition().setCarbohydrate(Double.parseDouble(value));
                    break;
                case CUTTING_STYLE:
                    currentVegetable.setCuttingStyle(CuttingStyle.valueOf(value.toUpperCase()));
                    break;
                case USING_SEEKS:
                    ((FruitVegetable) currentVegetable).setUsingSeeks(Boolean.valueOf(value));
                    break;
                case USING_LEAF:
                    ((LeafyVegetable) currentVegetable).setUsingLeaf(Boolean.valueOf(value));
                    break;
                case USING_STALK:
                    ((LeafyVegetable) currentVegetable).setUsingStalk(Boolean.valueOf(value));
                    break;
            }
        }
        currentEnum = null;
    }

}
