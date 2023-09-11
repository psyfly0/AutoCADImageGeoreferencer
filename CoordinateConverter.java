/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.imagestoautocad.imagecoordinatestoautocad;

import org.locationtech.proj4j.CRSFactory;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.CoordinateTransform;
import org.locationtech.proj4j.CoordinateTransformFactory;
import org.locationtech.proj4j.ProjCoordinate;

/**
 *
 * @author szaboa
 */
public class CoordinateConverter {
    private CoordinateTransform transform;

    public CoordinateConverter() {
        // Define the source and target coordinate reference systems (CRS)
        CRSFactory crsFactory = new CRSFactory();
        CoordinateReferenceSystem sourceCrs = crsFactory.createFromName("epsg:4326"); // WGS84
        CoordinateReferenceSystem targetCrs = crsFactory.createFromName("epsg:23700"); // EOV 

        // Create a coordinate transform factory
        CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();

        // Create the coordinate transform
        transform = ctFactory.createTransform(sourceCrs, targetCrs);
    }

    // Convert WGS84 (latitude, longitude) to EOV (east, north)
    public double[] wgs84ToEOV(double latitude, double longitude) {
        ProjCoordinate sourceCoord = new ProjCoordinate(longitude, latitude);
        ProjCoordinate targetCoord = new ProjCoordinate();
        transform.transform(sourceCoord, targetCoord);
        return new double[]{targetCoord.x, targetCoord.y};
    }
    
}
