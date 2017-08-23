package com.tmobile.pde.model;

import java.io.Serializable;

public class Promotion {
    private long promoId;
    private String promoTitle;
    private String promoDesc;

    public long getPromoId() {
        return promoId;
    }

    public void setPromoId(long promoId) {
        this.promoId = promoId;
    }

    public String getPromoTitle() {
        return promoTitle;
    }

    public void setPromoTitle(String promoTitle) {
        this.promoTitle = promoTitle;
    }

    public String getPromoDesc() {
        return promoDesc;
    }

    public void setPromoDesc(String promoDesc) {
        this.promoDesc = promoDesc;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "promoId=" + promoId +
                ", promoTitle='" + promoTitle + '\'' +
                ", promoDesc='" + promoDesc + '\'' +
                '}';
    }
}
