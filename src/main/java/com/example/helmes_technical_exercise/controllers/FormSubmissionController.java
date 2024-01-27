package com.example.helmes_technical_exercise.controllers;

import com.example.helmes_technical_exercise.entities.FormSubmission;
import com.example.helmes_technical_exercise.services.FormSubmissionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api/submissions")
public class FormSubmissionController {
    @Autowired
    private FormSubmissionService formSubmissionService;

    @PostMapping(value = "/submitForm", consumes = "application/x-www-form-urlencoded")
    public String submitForm(@ModelAttribute FormSubmission submission, RedirectAttributes redirectAttributes, HttpSession session) {
        FormSubmission savedSubmission = formSubmissionService.saveOrUpdateFormSubmission(submission);
        session.setAttribute("submissionId", savedSubmission.getId());
        redirectAttributes.addFlashAttribute("successMessage", "Data saved successfully!");
        return "redirect:/";
    }

}

