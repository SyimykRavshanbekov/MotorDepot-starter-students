package com.company.service;


import com.company.entities.Driver;
import com.company.entities.State;
import com.company.entities.Truck;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.company.Main.*;

public class ServiceImpl implements Service{
    List<Truck> trucks = new ArrayList<>(List.of(GSON.fromJson(readTtuck(), Truck[].class)));
    List<Driver> drivers = new ArrayList<>(List.of(GSON.fromJson(readDriver(),Driver[].class)));


    public List<Truck> getTrucks() {
        return trucks;
    }

    public void TrucksView(){
            System.out.println("#  |Truck              |State         |   Driver   ");

        for (Truck t: trucks) {
            System.out.println(t.getId() + "  |  " + t.getTruckName() +"         |  " + t.getState() + "      |" + t.getDriver());
        }
    }

    public void seeDrivers(){

        System.out.println();

        for (Driver d: drivers) {
            System.out.println(d.getIdDiver() + "  |  " + d.getName() + "  |  " + d.getTruckName());
        }

        System.out.println("------------------------------------");
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void searchTruck(int ID){
        System.out.println("\n----------------------------");
        Optional<Truck> optional = trucks.stream().filter(x -> x.getId() == ID).findFirst();

        if (optional.isPresent()) optional.ifPresent(x -> System.out.println("ID: " + x.getId() + "\nTrack name: " + x.getTruckName() + "\nDriver: " + x.getDriver() + "\nState: " + x.getState()));
        else System.out.println("Track not found!");
    }

    @Override
    public void changeDriver(int truckId) {
        for (Truck x : trucks) {
            if (x.getId() == truckId && x.getState() == State.BASE || x.getState() == State.REPAIR) {
                int counter = 0;
                for (Driver d : drivers) {
                    if (d.getTruckName().equals(x.getTruckName())){
                       d.setTruckName("null");
                    }
                    
                    if (d.getTruckName().equals("") && counter == 0) {
                        counter++;
                        x.setDriver(d.getName());
                        d.setTruckName(x.getTruckName());
                    }

                    if (d.getTruckName().equals("null")){
                        d.setTruckName("");
                    }
                }
            }
        }
    }


    @Override
    public void startDriving(int truckId) {
        Optional<Truck> optional = trucks.stream().filter(x -> x.getId() == truckId &&  !x.getDriver().equals(" ")).findFirst();

        if (optional.isPresent()) {
            optional.ifPresent(x -> x.setState(State.ROUTE));
            System.out.println("-----Truck on the road------");
        } else {
            System.out.println("Track not found or truck don't have driver!");
        }
    }

    @Override
    public void startRepair(int truckId) {
        Optional<Truck> optional = trucks.stream().filter(x -> x.getId() == truckId).findFirst();

        if (optional.isPresent()) {
            optional.ifPresent(x -> x.setState(State.REPAIR));
            System.out.println("-----Truck on the repair------");
        }
        else System.out.println("Track not found!");
    }

    @Override
    public void changeTruckState(int truckId) {
        Optional<Truck> optional = trucks.stream().filter(x -> x.getId() == truckId).findFirst();

        if (optional.isPresent()) {
            optional.ifPresent(x -> x.setState(State.BASE));
            System.out.println("-----Truck is base------");
        }
        else System.out.println("Track not found!");
    }
}












