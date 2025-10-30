package com.example.vtucgpa.controller;

import com.example.vtucgpa.model.BadCGPASemesterRequest;
import com.example.vtucgpa.model.BadCGPASemesterResult;
import com.example.vtucgpa.service.BadCGPAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BadCGPAController {

    @Autowired
    private BadCGPAService badCGPAService;

    @GetMapping("/bad-cgpa")
    public String showForm(Model model) {
        model.addAttribute("request", new BadCGPASemesterRequest());
        return "bad-cgpa";
    }

    @PostMapping("/bad-cgpa")
    public String calculateCGPA(@ModelAttribute("request") BadCGPASemesterRequest request, Model model) {
        BadCGPASemesterResult result = badCGPAService.calculateCGPAAndPercentage(request);
        model.addAttribute("result", result);
        model.addAttribute("request", request);
        return "bad-cgpa";
    }
}

