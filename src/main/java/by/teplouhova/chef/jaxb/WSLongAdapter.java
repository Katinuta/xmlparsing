package by.teplouhova.chef.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class WSLongAdapter extends XmlAdapter<String, Long> {
    @Override
    public String marshal(Long id) throws Exception {
        if (id == null) {
            return "";
        }
        return "c" + id.toString();
    }

    @Override
    public Long unmarshal(String id) throws Exception {
        return Long.parseLong(id.substring(1));
    }
}