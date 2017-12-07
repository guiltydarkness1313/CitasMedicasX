package com.shibuyaxpress.citasmedicasx.models;

/**
 * Created by paulf on 17-Nov-17.
 */

public class ResponsePatient {
    private String status;
    private Pacientes[] data;

    public ResponsePatient() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Pacientes[] getData() {
        return data;
    }

    public void setData(Pacientes[] data) {
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
