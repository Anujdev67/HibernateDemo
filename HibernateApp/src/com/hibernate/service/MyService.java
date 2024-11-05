package com.hibernate.service;

import java.util.List;
import java.util.stream.Collectors;
import com.hibernate.exception.InvalidMarksException;

public class MyService {
    public double computePercent(List<Double> listMarks, double totalMarks) throws InvalidMarksException {
        // Check for negative marks
        for (Double mark : listMarks) {
            if (mark < 0) {
                throw new InvalidMarksException("Marks cannot be negative");
            }
        }

        double totalMarksScored = listMarks.stream().collect(Collectors.summingDouble(e -> e));
        double percent = (totalMarksScored / totalMarks) * 100;

        // Check if percent is valid
        if (percent > 100) {
            throw new InvalidMarksException("Percent cannot be > 100");
        }

        return percent;
    }
}
