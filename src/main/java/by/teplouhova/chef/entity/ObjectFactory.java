package by.teplouhova.chef.entity;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
    private final static QName _Leafyvegetable_QNAME = new QName("http://www.example.com/salad", "leafy-vegetable");
    private final static QName _Rootcrop_QNAME = new QName("http://www.example.com/salad", "rootcrop");
    private final static QName _Fruitvegetable_QNAME = new QName("http://www.example.com/salad", "fruit-vegetable");
    private final static QName _Vegetable_QNAME = new QName("http://www.example.com/salad", "vegetable");

    public ObjectFactory() {
    }

    public Salad createSalad() {
        return new Salad();
    }

//    public Vegetable createVegetable(){
//        return new Vegetable() ;
//    }

    public RootCrop createRootCrop() {
        return new RootCrop();
    }

    public LeafyVegetable createLeafyVegetable() {
        return new LeafyVegetable();
    }

    public FruitVegetable createFruitVegetable() {
        return new FruitVegetable();
    }

    public Vegetable.Composition createComposition() {
        return new Vegetable.Composition();
    }

    @XmlElementDecl(namespace = "http://www.example.com/salad", name = "vegetable")
    public JAXBElement<Vegetable> createVegetable(Vegetable value) {

        return new JAXBElement<>(_Vegetable_QNAME, Vegetable.class, null, value);
    }

    @XmlElementDecl(namespace = "http://www.example.com/salad", name = "rootcrop",
            substitutionHeadNamespace = "http://www.example.com/salad",
            substitutionHeadName = "vegetable")
    public JAXBElement<RootCrop> createRootCrop(RootCrop value) {
        return new JAXBElement<>(_Rootcrop_QNAME, RootCrop.class, null, value);
    }

    @XmlElementDecl(namespace = "http://www.example.com/salad", name = "leafy-vegetable",
            substitutionHeadNamespace = "http://www.example.com/salad",
            substitutionHeadName = "vegetable")
    public JAXBElement<LeafyVegetable> createLeafyVegetable(LeafyVegetable value) {
        return new JAXBElement<>(_Leafyvegetable_QNAME, LeafyVegetable.class, null, value);
    }

    @XmlElementDecl(namespace = "http://www.example.com/salad", name = "fruit-vegetable",
            substitutionHeadNamespace = "http://www.example.com/salad",
            substitutionHeadName = "vegetable")
    public JAXBElement<FruitVegetable> createFruitVegetable(FruitVegetable value) {
        return new JAXBElement<>(_Fruitvegetable_QNAME, FruitVegetable.class, null, value);
    }
}

