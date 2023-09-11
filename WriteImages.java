/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.imagestoautocad.imagecoordinatestoautocad;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author szaboa
 */
public class WriteImages {
    public void writeGPSDataToFile(String fileName, List<GpsInfo> gpsInfoList) {
        try {
            FileWriter writer = new FileWriter(fileName);
            CoordinateConverter c = new CoordinateConverter();

            for (GpsInfo gpsInfo : gpsInfoList) {
                writer.write("-IMAGE\n");
                writer.write("C\n"); // attach mode : C (Csatol)
                writer.write(gpsInfo.getFilePath() + "\n"); // full path              
                 // Convert WGS84 coordinates to EOV
                double[] eovCoordinates = c.wgs84ToEOV(gpsInfo.getLatitude(), gpsInfo.getLongitude());
                writer.write(eovCoordinates[0] + "," + eovCoordinates[1] + "\n"); // Transformed coordinates
                writer.write("1\n"); // scale
                writer.write("0\n"); // rotation
            }

            writer.close();
            System.out.println("Data has been written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error writing data to " + fileName);
        }
    }
}
