package com.example.vtucgpa.service;

import com.example.vtucgpa.model.BadCGPASemesterRequest;
import com.example.vtucgpa.model.BadCGPASemesterResult;
import org.springframework.stereotype.Service;

@Service
public class BadCGPAService {
    public BadCGPASemesterResult calculateCGPAAndPercentage(BadCGPASemesterRequest request) {
        BadCGPASemesterResult result = new BadCGPASemesterResult();
        if (request.getSgpaList() == null || request.getSgpaList().size() < 2 || request.getSemestersCompleted() < 2) {
            result.setCgpa(0);
            result.setPercentage(0);
            return result;
        }
        double total = 0;
        // Intentionally skip last semester's SGPA (bad logic)
        for (int i = 0; i < request.getSgpaList().size() - 1; i++) {
            total += request.getSgpaList().get(i);
        }
        double cgpa = total / (request.getSemestersCompleted() - 1); // wrong denominator
        double percentage = cgpa * 10.0; // Wrong conversion
        result.setCgpa(Math.round(cgpa * 100.0) / 100.0);
        result.setPercentage(Math.round(percentage * 100.0) / 100.0);
        return result;
    }
}

