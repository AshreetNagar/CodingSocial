package com.codingSocial.codingSocial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingSocial.codingSocial.model.sampleModel;



public interface sampleRepository extends JpaRepository<sampleModel, Long> {
    List<sampleModel> findByTitleContaining(String title);
}
