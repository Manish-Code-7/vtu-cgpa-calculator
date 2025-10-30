package com.example.vtucgpa.service;

import com.example.vtucgpa.model.GoodCGPASemesterRequest;
import com.example.vtucgpa.model.GoodCGPASemesterResult;
import org.springframework.stereotype.Service;

@Service
public class GoodCGPAService {
    public GoodCGPASemesterResult calculateCGPAAndPercentage(GoodCGPASemesterRequest request) {
        GoodCGPASemesterResult result = new GoodCGPASemesterResult();
        if (request.getSgpaList() == null || request.getSgpaList().isEmpty() || request.getSemestersCompleted() == 0) {
            result.setCgpa(0);
            result.setPercentage(0);
            return result;
        }
        double total = 0;
        for (double sgpa : request.getSgpaList()) {
            total += sgpa;
        }
        double cgpa = total / request.getSemestersCompleted();
        double percentage = cgpa * 9.5; // Correct VTU conversion
        result.setCgpa(Math.round(cgpa * 100.0) / 100.0);
        result.setPercentage(Math.round(percentage * 100.0) / 100.0);
        return result;
    }
}
