package com.company.service;

public interface Service {

    void changeDriver(int truckId);

    void startDriving(int truckId);

    void startRepair(int truckId);

    void changeTruckState(int truckId);

}
