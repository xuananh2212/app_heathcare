package com.example.heathcare_app.model;

public class BodyUpdate<T> {
    private  int idOrder;
    private T payload;

    public BodyUpdate(int idOrder, T payload) {
        this.idOrder = idOrder;
        this.payload = payload;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "BodyUpdate{" +
                "idOrder=" + idOrder +
                ", payload=" + payload +
                '}';
    }
}
