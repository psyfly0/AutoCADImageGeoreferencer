/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.imagestoautocad.imagecoordinatestoautocad;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.Rational;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author szaboa
 */
public class ReadImages {
    private List<GpsInfo> gpsInfoList = new ArrayList<>();

    public void readImagesFromFolder(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && isJpgImage(file)) {
                    try {
                        Metadata metadata = ImageMetadataReader.readMetadata(file);
                        GpsDirectory gpsDirectory = metadata.getFirstDirectoryOfType(GpsDirectory.class);

                        if (gpsDirectory != null) {
                            // Extract GPS information
                            Rational[] latitudeArray = gpsDirectory.getRationalArray(GpsDirectory.TAG_LATITUDE);
                            Rational[] longitudeArray = gpsDirectory.getRationalArray(GpsDirectory.TAG_LONGITUDE);

                            if (latitudeArray != null && longitudeArray != null && latitudeArray.length == 3 && longitudeArray.length == 3) {
                                double latitude = latitudeArray[0].doubleValue() +
                                        latitudeArray[1].doubleValue() / 60 +
                                        latitudeArray[2].doubleValue() / 3600;

                                double longitude = longitudeArray[0].doubleValue() +
                                        longitudeArray[1].doubleValue() / 60 +
                                        longitudeArray[2].doubleValue() / 3600;

                                // Store GPS information in the list
                                gpsInfoList.add(new GpsInfo(file.getAbsolutePath(), latitude, longitude));
                            }
                        } else {
                            System.out.println("GPS information not found in " + file.getName());
                        }
                    } catch (ImageProcessingException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private boolean isJpgImage(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".jpeg");
    }

    // Getter for the list of GPS information
    public List<GpsInfo> getGpsInfoList() {
        return gpsInfoList;
    }

}
