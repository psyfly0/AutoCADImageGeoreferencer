/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.imagestoautocad.imagecoordinatestoautocad;

/**
 *
 * @author szaboa
 */
public class GpsInfo {
    private String filePath;
    private double latitude;
    private double longitude;

    public GpsInfo(String filePath, double latitude, double longitude) {
        this.filePath = filePath;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getFilePath() {
        return filePath;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
