package com.example.myphonestore.controllers;


import com.example.myphonestore.entities.Auricular;
import com.example.myphonestore.services.AuricularServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/auriculares")
public class AuricularController  extends BaseControllerImpl<Auricular, AuricularServiceImpl>{
}
