package util;

import entity.DateTimeRange;

public class Calculator {
    public static double calculateCost(DateTimeRange range, double costPerHour) {
        return range.getDurationInHours() * costPerHour;
    }
}