package com.example.ray.codecollections.view.recycleractivity.hfrecyclerfragment;

public class RecyclerBean {
    private int type;
    private String title;
    private int imageSource;
    private boolean isSelect;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageSource() {
        return imageSource;
    }

    public void setImageSource(int imageSource) {
        this.imageSource = imageSource;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @Override
    public String toString() {
        return "RecyclerBean{" +
                "title='" + title + '\'' +
                ", imageSource=" + imageSource +
                '}';
    }
}
