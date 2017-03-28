package com.bawei.dianshang.bean;

/**
 * Created by 1 on 2017/3/16.
 */
public class TitleLunbo
{
    private String image;
    private String uri;

    public TitleLunbo(String image, String uri) {
        this.image = image;
        this.uri = uri;
    }

    public TitleLunbo() {
        super();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "TitleLunbo{" +
                "image='" + image + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}
