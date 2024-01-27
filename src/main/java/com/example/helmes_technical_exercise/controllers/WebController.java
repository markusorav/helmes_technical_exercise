package com.example.helmes_technical_exercise.controllers;

import com.example.helmes_technical_exercise.entities.FormSubmission;
import com.example.helmes_technical_exercise.entities.Sector;
import com.example.helmes_technical_exercise.services.FormSubmissionService;
import com.example.helmes_technical_exercise.services.SectorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WebController {

    @Autowired
    private FormSubmissionService formSubmissionService;

    @Autowired
    private SectorService sectorService;

    @GetMapping("/")
    public String index(Model model, HttpSession session, @ModelAttribute("successMessage") String successMessage) {
        List<Sector> sectors = sectorService.getAllSectors();
        model.addAttribute("sectors", sectors);
        model.addAttribute("successMessage", successMessage);

        if (session.getAttribute("submissionId") != null) {
            Integer submissionId = (Integer) session.getAttribute("submissionId");
            FormSubmission existingSubmission = formSubmissionService.fetchSubmission(submissionId);
            model.addAttribute("submission", existingSubmission);
        } else {
            model.addAttribute("submission", new FormSubmission());
        }

        return "index";
    }
}