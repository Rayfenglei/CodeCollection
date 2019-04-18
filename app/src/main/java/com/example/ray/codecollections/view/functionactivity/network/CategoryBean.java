package com.example.ray.codecollections.view.functionactivity.network;

public class CategoryBean {
    private String nameType;
    private String apiUrl;
    private String name;
    private String tabType;
    private String id;
    private String adTrack;

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTabType() {
        return tabType;
    }

    public void setTabType(String tabType) {
        this.tabType = tabType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdTrack() {
        return adTrack;
    }

    public void setAdTrack(String adTrack) {
        this.adTrack = adTrack;
    }

    @Override
    public String toString() {
        return "CategoryBean{" +
                "nameType='" + nameType + '\'' +
                ", apiUrl='" + apiUrl + '\'' +
                ", name='" + name + '\'' +
                ", tabType='" + tabType + '\'' +
                ", id='" + id + '\'' +
                ", adTrack='" + adTrack + '\'' +
                '}';
    }
}
