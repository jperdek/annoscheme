package com.crustlab.aop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import battleship.Battleship;
//import configurationManagement.ConfigurationLoader;
import com.crustlab.aop.application.src.main.java.battleship.Battleship;


@Service
public class ImportedService {
    
    public Battleship battleship;

    @Autowired
    public ImportedService(Battleship battleship) {
            this.battleship= battleship;
    }

    public void run() {
        ConfigurationLoader configurationLoader = new ConfigurationLoader("./resources/battleshipConfig.json");
        Battleship battleship = new Battleship();
        battleship.start();
    }
}
