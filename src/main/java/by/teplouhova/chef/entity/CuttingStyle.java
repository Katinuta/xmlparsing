package by.teplouhova.chef.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlEnum
@XmlType(name = "cutting-style-type")
public enum CuttingStyle {
    @XmlEnumValue("finedice")
    FINEDICE,
    @XmlEnumValue("shreading")
    SHREADING,
    @XmlEnumValue("matchsticks")
    MATCHSTICKS,
    @XmlEnumValue("largedice")
    LARGEDICE,
    @XmlEnumValue("slicing")
    SLICING,
    @XmlEnumValue("rollcutting")
    ROLLCUTTING
}
