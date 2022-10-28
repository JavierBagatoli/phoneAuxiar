package com.example.myphonestore.controllers;

import com.example.myphonestore.entities.Celular;
import com.example.myphonestore.services.CelularServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/calulares")

public class CelularController extends BaseControllerImpl<Celular, CelularServiceImpl> {
}
