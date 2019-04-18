package com.example.ray.codecollections.view.recycleractivity.stickyrecyclerfragment;

public class StickyRecyclerBean {
    private String name;
    private String province;

    StickyRecyclerBean(String name, String province) {
        this.name = name;
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "StickyRecyclerBean{" +
                "name='" + name + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}
