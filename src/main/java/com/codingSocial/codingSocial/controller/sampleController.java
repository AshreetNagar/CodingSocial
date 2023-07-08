package com.codingSocial.codingSocial.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import lombok.AllArgsConstructor;

import com.codingSocial.codingSocial.repository.sampleRepository;
import com.codingSocial.codingSocial.model.sampleModel;


@RestController
@CrossOrigin
@RequestMapping("api/")
@AllArgsConstructor
public class sampleController {

    private final sampleRepository sampleRepository;

    @GetMapping("/addSample")
    public ResponseEntity<?> addSample(){

        sampleModel newSample = new sampleModel();
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm").format(new java.util.Date());
        newSample.setTitle("New Sample at: "+timeStamp);
        sampleRepository.save(newSample);
        return ResponseEntity.ok().body("New Sample at: "+timeStamp);
    }

    @GetMapping("/listSamples")
    public ResponseEntity<?> listSamples(){
        List<sampleModel> allSamples = sampleRepository.findAll();
        return ResponseEntity.ok().body(allSamples);
    }

}
