package com.shibuyaxpress.citasmedicasx.models;

import com.alamkanak.weekview.WeekViewEvent;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by ebenites on 24/04/2017.
 */

public class MyWeekViewEvent extends WeekViewEvent {

    private String doctor;

    private String especialidad;

    private String tipo;

    private String topic;

    public String getTeacher() {
        return doctor;
    }

    public void setTeacher(String doctor) {
        this.doctor = doctor;
    }

    public String getSection() {
        return especialidad;
    }

    public void setSection(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getType() {
        return tipo;
    }

    public void setType(String tipo) {
        this.tipo = tipo;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDatetime(){
        if(this.getStartTime() == null || this.getEndTime() == null)
            return "";
        String day = new SimpleDateFormat("EEEE ", new Locale("es","ES")).format(this.getStartTime().getTime());
        String start = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(this.getStartTime().getTime());
        String end = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(this.getEndTime().getTime());
        return day.toUpperCase() + " " + start + " - " + end;
    }

    @Override
    public String toString() {
        return "MyWeekViewEvent{" +
                "doctor='" + doctor + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", tipo='" + tipo + '\'' +
                ", topic='" + topic + '\'' +
                '}';
    }
}
