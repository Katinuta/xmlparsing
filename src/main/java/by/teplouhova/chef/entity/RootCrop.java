package by.teplouhova.chef.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rootcrop", propOrder = {"deletingCork"})

public class RootCrop extends Vegetable {
    @XmlElement(name = "deleting-cork", required = true)
    private boolean deletingCork;

    public RootCrop() {
    }

    public RootCrop(long vegetableId, String vegetableName, int caloricity, int weight, Composition composition,
                    WayCooking wayCooking, CuttingStyle cuttingStyle, boolean deletingCork) {
        super(vegetableId, vegetableName, caloricity, weight, composition, wayCooking, cuttingStyle);
        this.deletingCork = deletingCork;
    }

    public boolean isDeletingCork() {
        return deletingCork;
    }

    public void setDeletingCork(boolean deletingCork) {
        this.deletingCork = deletingCork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RootCrop rootCrop = (RootCrop) o;

        if (!super.equals(o)) return false;

        return deletingCork == rootCrop.deletingCork;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        return 31 * result + (deletingCork ? 1 : 0);
    }
}
