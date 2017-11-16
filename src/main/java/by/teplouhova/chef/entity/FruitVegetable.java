package by.teplouhova.chef.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fruit-vegetable", propOrder = {"usingSeeks"})
public class FruitVegetable extends Vegetable {

    @XmlElement(name = "using-seeks")
    protected boolean usingSeeks;

    public FruitVegetable() {
    }

    public FruitVegetable(long vegetableId, String vegetableName, int caloricity, int weight,
                          Composition composition, WayCooking wayCooking,
                          CuttingStyle cuttingStyle, boolean usingSeeks) {
        super(vegetableId, vegetableName, caloricity, weight, composition, wayCooking, cuttingStyle);
        this.usingSeeks = usingSeeks;
    }


    public boolean isUsingSeeks() {
        return usingSeeks;
    }

    public void setUsingSeeks(boolean usingSeeks) {
        this.usingSeeks = usingSeeks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FruitVegetable that = (FruitVegetable) o;

        if (!super.equals(o)) return false;

        return usingSeeks == that.usingSeeks;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        return 31 * result + (usingSeeks ? 1 : 0);
    }

    @Override
    public String toString() {
        return "FruitVegetable{" + super.toString() +
                "usingSeeks=" + usingSeeks +
                '}';
    }
}
