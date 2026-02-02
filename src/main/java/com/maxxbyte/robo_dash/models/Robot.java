package com.maxxbyte.robo_dash.models;

public class Robot {

    //TODO: implement battery drain based on distance measurement
    int battery = 100;
    int walkwaySpeed = 15;
    int roadwaySpeed = 30;
    boolean isOnDelivery;
    boolean turnSignal = false;
    Order currentOrder;



}
