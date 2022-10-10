package com.company;

import com.company.service.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Main {
    final static Scanner scannerS = new Scanner(System.in);
    final static Scanner scannerN = new Scanner(System.in);
    final static Scanner scannerC = new Scanner(System.in);
    final static Scanner scannerB = new Scanner(System.in);
    public static final GsonBuilder BUILDER = new GsonBuilder();
    public static final Gson GSON = BUILDER.setPrettyPrinting().create();
    public static final Path WRITE_PATH = Paths.get("./truck.json");
    public static final Path WRITE_PATH1 = Paths.get("./driver.json");

    public static void main(String[] args) throws Exception {
        ServiceImpl service = new ServiceImpl();

        String command = "null";

        while(!command.equals("x")){
            service.TrucksView();
            System.out.println("Choose driver: (INTEGER)");
            int truckID = scannerB.nextInt();
            service.searchTruck(truckID);

            buttons();
            command = scannerC.nextLine();
            switch (command){
                case "1" -> {
                    service.changeDriver(truckID);
                    service.seeDrivers();
                }
                case "2" -> service.startDriving(truckID);
                case "3" -> service.startRepair(truckID);
                case "4" -> service.changeTruckState(truckID);
            }
        }
    }

    public static void buttons(){
        System.out.println("\n-----------------------------------");
        System.out.println("Press 1 to change Driver\n" +
        "Press 2 to send to the Route\n" +
        "Press 3 to send to the Repairing\n" +
                "Press 4 to change truck state to Base");
        System.out.println("-----------------------------------");
        System.out.println("Choose command:");
    }


   public static String readTtuck() {
       return getString(WRITE_PATH);
   }

   public static String readDriver() {
       return getString(WRITE_PATH1);
   }

    private static String getString(Path writePath1) {
        StringBuilder json = new StringBuilder();
        try (FileReader fr = new FileReader(String.valueOf(writePath1))){
            int a;
            while ((a = fr.read()) != -1) {
                json.append((char) a);
            }
            return json.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return json.toString();
    }
}