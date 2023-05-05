package org.exploremore;

public class Location {
    int i = 0;
    String[] type = new String[256];
    String[] name = new String[256];
    String[] address = new String[256];
    Double[] rating = new Double[256];
    public void setType(String t, int i) {
        type[i] = t;
    }
    public void setName(String n, int i) {
        name[i] = n;
    }
    public void setAddress(String a, int i) {
        address[i] = a;
    }
    public void setRating(Double a, int i) { rating[i] = a;}
    public void setI(int x) {
        i = x;
    }
    public String[] getType() {
        return type;
    }

    public String[] getName() {
        return name;
    }

    public String[] getAddress() {
        return address;
    }
    public Double[] getRating() { return rating; }
}
