package com.example.myphonestore.controllers;

import com.example.myphonestore.entities.Parlante;
import com.example.myphonestore.services.ParlanteServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/parlantes")

public class ParlanteController extends BaseControllerImpl<Parlante, ParlanteServiceImpl> {
}
