package by.teplouhova.chef.entity;

import by.teplouhova.chef.jaxb.WSLongAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("WeakerAccess")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vegetable", propOrder = {"caloricity",
        "weight", "composition", "cuttingStyle"})

@XmlSeeAlso({LeafyVegetable.class, RootCrop.class, FruitVegetable.class})
public abstract class Vegetable {

    @XmlAttribute(name = "vegetable-id", required = true)
    @XmlJavaTypeAdapter(type = long.class, value = WSLongAdapter.class)
    @XmlID
    protected long vegetableId;

    @XmlAttribute(name = "vegetable-name", required = true)
    protected String vegetableName;

    @XmlElement(name = "caloricity", required = true)
    protected int caloricity;

    @XmlElement(name = "weight", required = true)
    protected int weight;

    @XmlElement(name = "composition", required = true)
    protected Composition composition;

    @XmlAttribute(name = "way-cooking")
    protected WayCooking wayCooking;

    @XmlElement(name = "cutting-style", required = true)
    private CuttingStyle cuttingStyle;


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "composition", propOrder = {"protein",
            "carbohydrate", "fat"})
    public static class Composition {
        @XmlElement(required = true)
        private double protein;
        @XmlElement(required = true)
        private double carbohydrate;
        @XmlElement(required = true)
        private double fat;

        public Composition() {
        }

        public Composition(double protein, double carbohydrate, double fat) {
            this.protein = protein;
            this.carbohydrate = carbohydrate;
            this.fat = fat;
        }

        public double getProtein() {
            return protein;
        }

        public void setProtein(double protein) {
            this.protein = protein;
        }

        public double getCarbohydrate() {
            return carbohydrate;
        }

        public void setCarbohydrate(double carbohydrate) {
            this.carbohydrate = carbohydrate;
        }

        public double getFat() {
            return fat;
        }

        public void setFat(double fat) {
            this.fat = fat;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Composition that = (Composition) o;

            if (Double.compare(that.protein, protein) != 0) return false;
            if (Double.compare(that.carbohydrate, carbohydrate) != 0) return false;
            return Double.compare(that.fat, fat) == 0;
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            temp = Double.doubleToLongBits(protein);
            result = (int) (temp ^ (temp >>> 32));
            temp = Double.doubleToLongBits(carbohydrate);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            temp = Double.doubleToLongBits(fat);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }

        @Override
        public String toString() {
            return "protein=" + protein +
                    ", carbohydrate=" + carbohydrate +
                    ", fat=" + fat;
        }
    }

    public Vegetable() {
        composition = new Composition();
        this.wayCooking = WayCooking.RAW;
    }

    public Vegetable(long vegetableId, String vegetableName, int caloricity, int weight, Composition composition, WayCooking wayCooking, CuttingStyle cuttingStyle) {
        this.vegetableId = vegetableId;
        this.vegetableName = vegetableName;
        this.caloricity = caloricity;
        this.weight = weight;
        this.composition = composition;
        this.wayCooking = wayCooking;
        this.cuttingStyle = cuttingStyle;
    }
    //    public long getVegetableId() {
//        return vegetableId;
//    }

    public void setVegetableId(String vegetableId) {
        //this.vegetableId=vegetableId;
        this.vegetableId = Long.parseLong(vegetableId.substring(1));
    }

    public String getVegetableName() {
        return vegetableName;
    }

    public void setVegetableName(String vegetableName) {
        this.vegetableName = vegetableName;
    }

    public int getCaloricity() {
        return caloricity;
    }

    public void setCaloricity(int caloricity) {
        this.caloricity = caloricity;
    }

    public Composition getComposition() {
        return composition;
    }

    public void setComposition(Composition composition) {
        this.composition = composition;
    }

    public WayCooking getWayCooking() {
        if (wayCooking == null) {
            return WayCooking.RAW;
        } else {
            return wayCooking;
        }
    }

    public void setWayCooking(WayCooking wayCooking) {

        this.wayCooking = wayCooking;
    }

    public CuttingStyle getCuttingStyle() {
        return cuttingStyle;
    }

    public void setCuttingStyle(CuttingStyle cuttingStyle) {
        this.cuttingStyle = cuttingStyle;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vegetable vegetable = (Vegetable) o;

        if (vegetableId != vegetable.vegetableId) return false;
        if (caloricity != vegetable.caloricity) return false;
        if (weight != vegetable.weight) return false;
        if (vegetableName != null ? !vegetableName.equals(vegetable.vegetableName) : vegetable.vegetableName != null)
            return false;
        if (composition != null ? !composition.equals(vegetable.composition) : vegetable.composition != null)
            return false;
        if (wayCooking != vegetable.wayCooking) return false;
        return cuttingStyle == vegetable.cuttingStyle;
    }

    @Override
    public int hashCode() {
//        int result = (int) (vegetableId ^ (vegetableId >>> 32));
//        result = 31 * result + (vegetableName != null ? vegetableName.hashCode() : 0);
//        result = 31 * result + caloricity;
//        result = 31 * result + weight;
//        result = 31 * result + (composition != null ? composition.hashCode() : 0);
//        result = 31 * result + (wayCooking != null ? wayCooking.hashCode() : 0);
//        result = 31 * result + (cuttingStyle != null ? cuttingStyle.hashCode() : 0);
//        return result;
        return 1;
    }

    @Override
    public String toString() {
        return "Vegetable{" +
                "vegetableId=" + vegetableId +
                ", vegetableName='" + vegetableName + '\'' +
                ", caloricity=" + caloricity +
                ", weight=" + weight +
                ", composition=" + composition +
                ", wayCooking=" + wayCooking +
                ", cuttingStyle=" + cuttingStyle +
                '}';
    }
}
