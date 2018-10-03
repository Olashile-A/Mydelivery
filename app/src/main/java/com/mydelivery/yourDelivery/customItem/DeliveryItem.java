package com.mydelivery.yourDelivery.customItem;

public class DeliveryItem {
    public String getNameDelivery() {
        return nameDelivery;
    }

    public void setNameDelivery(String nameDelivery) {
        this.nameDelivery = nameDelivery;
    }

    public String getAddressDelivery() {
        return addressDelivery;
    }

    public void setAddressDelivery(String addressDelivery) {
        this.addressDelivery = addressDelivery;
    }

    public int getImageDelivery() {
        return imageDelivery;
    }

    public void setImageDelivery(int imageDelivery) {
        this.imageDelivery = imageDelivery;
    }

    String nameDelivery;
    String addressDelivery;
    int imageDelivery;

    public DeliveryItem(int imageDelivery, String nameDelivery, String addressDelivery) {
        this.imageDelivery = imageDelivery;
        this.nameDelivery = nameDelivery;
        this.addressDelivery = addressDelivery;
    }
}
