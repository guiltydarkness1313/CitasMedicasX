package com.shibuyaxpress.citasmedicasx.models;

/**
 * Created by paulf on 17-Nov-17.
 */

public class ResponseUser {
    private String status;
    private Usuarios data;

    public ResponseUser() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuarios getData() {
        return data;
    }

    public void setData(Usuarios data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseUser{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
