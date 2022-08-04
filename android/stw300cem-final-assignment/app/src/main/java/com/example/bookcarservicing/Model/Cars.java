package com.example.bookcarservicing.Model;

public class Cars {

    private String carname;
    private String tyre;
    private String manufacturer;
    private String assembly;
    private String engine;
    private String power;
    private String model;
    private String mileage;
    private String fuel;
    private String abs;
    private String wheels;
    private String carimg;
    private int imageId;

    public Cars( String tyre, String manufacturer, String model, String assembly, String abs, String wheels, String fuel, String mileage, String power, String engine, int imageId) {
        this.tyre = tyre;
        this.manufacturer = manufacturer;
        this.model = model;
        this.assembly = assembly;
        this.abs = abs;
        this.engine = engine;
        this.power = power;
        this.mileage = mileage;
        this.fuel = fuel;
        this.wheels = wheels;
        this.carname = carname;
        this.imageId = imageId;

    }
    public String getCarname(){
        return carname;
    }
    public void setCarname(String carname) {
        this.carname = carname;
    }


    public String getTyre() {
        return tyre;
    }

    public void setTyre(String tyre) {
        this.tyre = tyre;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAssembly() {
        return assembly;
    }

    public void setAssembly(String assembly) {
        this.assembly = assembly;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getAbs() { return abs; }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) { this.fuel = fuel; }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getWheels() {
        return wheels;
    }

    public void setWheels(String wheels) {
        this.wheels = wheels;
    }








    public String getCarimg() {
        return carimg;
    }
}