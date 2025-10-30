package com.example.vtucgpa.controller;

import com.example.vtucgpa.model.GoodCGPASemesterRequest;
import com.example.vtucgpa.model.GoodCGPASemesterResult;
import com.example.vtucgpa.service.GoodCGPAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GoodCGPAController {

    @Autowired
    private GoodCGPAService goodCGPAService;

    @GetMapping("/good-cgpa")
    public String showForm(Model model) {
        model.addAttribute("request", new GoodCGPASemesterRequest());
        return "good-cgpa";
    }

    @PostMapping("/good-cgpa")
    public String calculateCGPA(@ModelAttribute("request") GoodCGPASemesterRequest request, Model model) {
        GoodCGPASemesterResult result = goodCGPAService.calculateCGPAAndPercentage(request);
        model.addAttribute("result", result);
        model.addAttribute("request", request);
        return "good-cgpa";
    }
}
