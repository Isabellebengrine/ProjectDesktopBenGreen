package org.myself.DAL;

public class Rubrique {
    private int id;
    private String name;
    //private Rubrique parent;
    private String picture;

    public Rubrique() {
    }

    public Rubrique(int id, String name, String picture) {
        this.id = id;
        this.name = name;
        //this.parent = parent;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Rubrique getParent() {
//        return parent;
//    }
//
//    public void setParent(Rubrique parent) {
//        this.parent = parent;
//    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
