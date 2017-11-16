package by.teplouhova.chef.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "way-cooking-type")
@XmlEnum
public enum WayCooking {
    @XmlEnumValue("raw")
    RAW,
    @XmlEnumValue("fry")
    FRY,
    @XmlEnumValue("boil")
    BOIL,
    @XmlEnumValue("stew")
    STEW,
    @XmlEnumValue("steam")
    STEAM
}
