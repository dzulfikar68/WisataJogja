package com.digitcreativestudio.wisatajogja;

import java.io.Serializable;

public class WisataModel implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name, address, detail, image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
