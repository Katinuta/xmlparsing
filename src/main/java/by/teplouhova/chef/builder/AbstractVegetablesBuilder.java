package by.teplouhova.chef.builder;

import by.teplouhova.chef.entity.Vegetable;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractVegetablesBuilder {
    protected Set<Vegetable> salad;

    public AbstractVegetablesBuilder() {
        salad = new HashSet<>();
    }

    public AbstractVegetablesBuilder(Set<Vegetable> salad) {
        this.salad = salad;
    }

    public Set<Vegetable> getSalad() {
        return salad;
    }

    abstract public void buildSalad(String filename);
}
