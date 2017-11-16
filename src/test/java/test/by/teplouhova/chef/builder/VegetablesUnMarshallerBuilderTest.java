package test.by.teplouhova.chef.builder;

import by.teplouhova.chef.builder.impl.VegetablesUnMarshBuilder;
import by.teplouhova.chef.entity.CuttingStyle;
import by.teplouhova.chef.entity.Vegetable;
import by.teplouhova.chef.entity.WayCooking;
import by.teplouhova.chef.entity.FruitVegetable;
import by.teplouhova.chef.entity.LeafyVegetable;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class VegetablesUnMarshallerBuilderTest {
    private VegetablesUnMarshBuilder builder;
    private Set<Vegetable> actualSalad;
    private Set<Vegetable> expectedSalad;

    @BeforeClass
    public void before() {
        builder = new VegetablesUnMarshBuilder();
        //noinspection SpellCheckingInspection
        builder.buildSalad("testdata/vegetables.xml");
        actualSalad = builder.getSalad();
        expectedSalad = new HashSet<>();
        expectedSalad.add(new FruitVegetable(2, "tomato", 30, 300,
                new Vegetable.Composition(0.2, 11.1, 0.0),
                WayCooking.STEW, CuttingStyle.MATCHSTICKS, true));
        expectedSalad.add(new LeafyVegetable(3, "cabbage", 16, 1000,
                new Vegetable.Composition(1.2, 2.2, 0.2), WayCooking.RAW,
                CuttingStyle.SHREADING, true, true));

    }

    @Test
    public void setContainsVegetablesTest() {
        assertTrue(actualSalad.iterator().next() instanceof Vegetable);
    }

    @Test
    public void setContainsTypeFruitVegetableTest() {
        assertTrue(actualSalad.iterator().next() instanceof FruitVegetable);
    }

    @Test
    public void setContainsTypeLeafyVegetableTest() {
        Iterator<Vegetable> it = actualSalad.iterator();
        it.next();
        Vegetable vegetable = it.next();
        assertTrue(vegetable instanceof LeafyVegetable);
    }

    @Test
    public void parserUnMarshallerReturnsCorrectDataTest() {
        assertEquals(actualSalad, expectedSalad);
    }


    @AfterClass
    private void after() {
        builder = null;
        actualSalad = null;
        expectedSalad = null;
    }
}
