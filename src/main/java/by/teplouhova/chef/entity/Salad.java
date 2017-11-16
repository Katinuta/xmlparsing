package by.teplouhova.chef.entity;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "salad", propOrder = {"recipe"})
@XmlRootElement(name = "salad")
public class Salad {

    @XmlElementRef(name = "vegetable", namespace = "http://www.example.com/salad", type = JAXBElement.class)
    protected Set<JAXBElement<? extends Vegetable>> recipe;

    public Salad() {
        recipe = new HashSet<>();
    }

    public Iterator<JAXBElement<? extends Vegetable>> getIterator() {
        return recipe.iterator();
    }

    public boolean addVegetable(Vegetable vegetable) {
        return recipe.add(new ObjectFactory().createVegetable(vegetable));
    }

    public boolean removeVegetable(Vegetable vegetable) {
        return recipe.remove(new ObjectFactory().createVegetable(vegetable));
    }

    @Override
    public String toString() {
        return "Salad{" +
                "recipe=" + recipe +
                '}';
    }


}
