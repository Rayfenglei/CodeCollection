package com.example.ray.codecollections.view.recycleractivity.hfrecyclerfragment;

public class RecyclerBean {
    private String title;
    private int imageSource;

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

    @Override
    public String toString() {
        return "RecyclerBean{" +
                "title='" + title + '\'' +
                ", imageSource=" + imageSource +
                '}';
    }
}
