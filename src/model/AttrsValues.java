package model;

import java.util.ArrayList;

/**
 * Created by junior and ramon.
 */
public class AttrsValues {

    private ArrayList<AttrVal> itens;

    public AttrsValues() {
        itens = new ArrayList<>();
    }

    public void add(String attribute, Object value) {
        itens.add(new AttrVal(attribute, value));
    }

    public ArrayList<AttrVal> getItens() {
        return itens;
    }

    public void clear() {
        itens.clear();
    }

    public static class AttrVal {

        private String attribute;
        private Object value;

        public AttrVal(String attribute, Object value) {
            this.attribute = attribute;
            this.value = value;
        }

        public String getAttribute() {
            return attribute;
        }

        public Object getValue() {
            return value;
        }
    }
}