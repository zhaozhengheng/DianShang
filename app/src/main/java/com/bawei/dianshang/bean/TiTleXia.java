package com.bawei.dianshang.bean;

/**
 * Created by 1 on 2017/3/17.
 */
public class TiTleXia
{
    private String efficacy;
    private String goods_img;
    private String market_price;

    public TiTleXia(String market_price, String efficacy, String goods_img) {
        this.market_price = market_price;
        this.efficacy = efficacy;
        this.goods_img = goods_img;
    }

    public TiTleXia() {
        super();
    }

    public String getEfficacy() {
        return efficacy;
    }

    public void setEfficacy(String efficacy) {
        this.efficacy = efficacy;
    }

    public String getGoods_img() {
        return goods_img;
    }

    public void setGoods_img(String goods_img) {
        this.goods_img = goods_img;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    @Override
    public String toString() {
        return "TiTleXia{" +
                "efficacy='" + efficacy + '\'' +
                ", goods_img='" + goods_img + '\'' +
                ", market_price='" + market_price + '\'' +
                '}';
    }
}
