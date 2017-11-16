package by.teplouhova.chef.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("ALL")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "leafy-vegetable", propOrder = {"usingStalk", "usingLeaf"})
public class LeafyVegetable extends Vegetable {
    @SuppressWarnings("WeakerAccess")
    @XmlElement(name = "using-stalk", required = true)
    protected boolean usingStalk;
    @XmlElement(name = "using-leaf", required = true)
    protected boolean usingLeaf;

    public LeafyVegetable() {
    }

    public LeafyVegetable(long vegetableId, String vegetableName, int caloricity, int weight,
                          Composition composition, WayCooking wayCooking, CuttingStyle cuttingStyle,
                          boolean usingStalk, boolean usingLeaf) {
        super(vegetableId, vegetableName, caloricity, weight, composition, wayCooking, cuttingStyle);
        this.usingStalk = usingStalk;
        this.usingLeaf = usingLeaf;
    }

    public boolean isUsingStalk() {
        return usingStalk;
    }

    public void setUsingStalk(boolean usingStalk) {
        this.usingStalk = usingStalk;
    }

    public boolean isUsingLeaf() {
        return usingLeaf;
    }

    public void setUsingLeaf(boolean usingLeaf) {
        this.usingLeaf = usingLeaf;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LeafyVegetable that = (LeafyVegetable) o;
        if (!super.equals(o)) return false;

        if (usingStalk != that.usingStalk) return false;
        return usingLeaf == that.usingLeaf;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (usingStalk ? 1 : 0);
        result = 31 * result + (usingLeaf ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LeafyVegetable{" + super.toString() +
                "usingStalk=" + usingStalk +
                ", usingLeaf=" + usingLeaf +
                '}';
    }
}
