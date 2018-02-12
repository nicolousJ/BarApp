package com.aggreycliford.barapp.Models;

/**
 * Created by nico on 2/12/2018.
 */

public class CategoryModel {

    String cat_name;
    float price;

    String[]subctas;
    float[] subprices;

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String[] getSubctas() {
        return subctas;
    }

    public void setSubctas(String[] subctas) {
        this.subctas = subctas;
    }

    public float[] getSubprices() {
        return subprices;
    }

    public void setSubprices(float[] subprices) {
        this.subprices = subprices;
    }

    class sub_category{
        String subname;
        float subprice;

        public String getSubname() {
            return subname;
        }

        public void setSubname(String subname) {
            this.subname = subname;
        }

        public float getSubprice() {
            return subprice;
        }

        public void setSubprice(float subprice) {
            this.subprice = subprice;
        }
    }
}
