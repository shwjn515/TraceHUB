package edu.scse.tracehub.ui.dashboard;
public class Cat {
    //类型
    private String type;
    //图片资源 ID
    private int imgId;

    public Cat(String type, int imgId) {
        this.type = type;
        this.imgId = imgId;
    }

    public String getType() {
        return type;
    }

    public int getImgId() {
        return imgId;
    }
}