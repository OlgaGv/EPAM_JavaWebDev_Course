package com.training.task1.creator;

import com.training.task1.entity.Point;
import com.training.task1.exception.InvalidDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Class that creates points
 */
public final class PointFactory {

    public static Logger LOGGER = LogManager.getLogger(PointFactory.class);

    public Point createPoint(double x, double y) {
        return new Point(x, y);
    }

    public Point createPoint(double[] x) throws InvalidDataException {
        if (x.length == 2) {
            return createPoint(x[0], x[1]);
        } else {
            throw new InvalidDataException("Incorrect size of array! " +
                    "Creating a point requires a size of 2 elements!");
        }
    }
}
