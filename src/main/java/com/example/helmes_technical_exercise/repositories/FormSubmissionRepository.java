package com.example.helmes_technical_exercise.repositories;

import com.example.helmes_technical_exercise.entities.FormSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormSubmissionRepository extends JpaRepository<FormSubmission, Integer> {
}

