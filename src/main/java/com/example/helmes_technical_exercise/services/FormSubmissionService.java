package com.example.helmes_technical_exercise.services;

import com.example.helmes_technical_exercise.entities.FormSubmission;
import com.example.helmes_technical_exercise.entities.Sector;
import com.example.helmes_technical_exercise.repositories.FormSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FormSubmissionService {
    @Autowired
    private FormSubmissionRepository formSubmissionRepository;

    @Autowired
    private SectorService sectorService;

    public FormSubmission saveOrUpdateFormSubmission(FormSubmission submission) {
        List<Sector> sectors = getSectorsFromIds(submission);
        submission.setSectors(sectors);

        if (submission.getId() != null)     // Update existing submission
            return updateExistingSubmission(submission);
        else                                // Save new submission
            return formSubmissionRepository.save(submission);
    }

    private List<Sector> getSectorsFromIds(FormSubmission submission) {
        return submission.getSectorIds().stream()
                .map(id -> sectorService.findSectorById(id)
                        .orElseThrow(() -> new RuntimeException("Something went wrong!")))
                .collect(Collectors.toList());
    }

    private FormSubmission updateExistingSubmission(FormSubmission submission) {
        FormSubmission existingSubmission = fetchSubmission(submission.getId());

        // Update fields of the existing submission
        existingSubmission.setName(submission.getName());
        existingSubmission.setSectorIds(submission.getSectorIds());
        existingSubmission.setSectors(submission.getSectors());
        existingSubmission.setAgreeToTerms(submission.isAgreeToTerms());

        return formSubmissionRepository.save(existingSubmission);
    }

    public FormSubmission fetchSubmission(Integer submissionId) {
        Optional<FormSubmission> existingSubmission = formSubmissionRepository.findById(submissionId);
        if (existingSubmission.isPresent()) {
            FormSubmission submission = existingSubmission.get();
            submission.setSectorIds(submission.getSectors().stream()
                                                .map(Sector::getId)
                                                .collect(Collectors.toList()));
            return submission;
        }
        else {
            throw new RuntimeException("Submission not found with id: " + submissionId);
        }
    }
}

