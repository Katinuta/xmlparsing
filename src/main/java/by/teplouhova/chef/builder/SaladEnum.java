package by.teplouhova.chef.builder;

public enum SaladEnum {

    SALAD("salad"),
    FRUIT_VEGETABLE("fruit-vegetable"),
    LEAFY_VEGETABLE("leafy-vegetable"),
    VEGETABLE_ID("vegetable-id"),
    VEGETABLE_NAME("vegetable-name"),
    WAY_COOKING("way-cooking"),
    CALORICITY("caloricity"),
    WEIGHT("weight"),
    COMPOSITION("composition"),
    PROTEIN("protein"),
    CARBOHYDRATE("carbohydrate"),
    FAT("fat"),
    CUTTING_STYLE("cutting-style"),
    USING_SEEKS("using-seeks"),
    USING_STALK("using-stalk"),
    USING_LEAF("using-leaf");

    private String value;

    private SaladEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}

