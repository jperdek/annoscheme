package com.crustlab.aop.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crustlab.aop.aspect.DiagramCoordinationCommand;


@RestController
public class FunctionalityMeasureController {

  private final DiagramCoordinationCommand diagramCoordinationCommand;

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @Autowired
  public FunctionalityMeasureController(DiagramCoordinationCommand diagramCoordinationCommand) {
        this.diagramCoordinationCommand = diagramCoordinationCommand;
  }

 

  @GetMapping("/staticCreation")
  public String staticCreation(@RequestParam(name="name", defaultValue = "World") String name) {
    System.out.println("Launching application");
    //DiagramCoordinationCommand diagramCoordinationCommand = new DiagramCoordinationCommand();
    this.diagramCoordinationCommand.initializeStaticPhaseDiagram();
    System.out.println("Terminating application");
    return "Greetings from Spring Boot!";
  }

  /* 
   //@Action(actionType = ActionType.START, message = "d1.startBattleship", diagramIdentifiers = {"d1.id"})
	//@Action(actionType = ActionType.END, message = "d1.endBattleship", diagramIdentifiers = {"d1.id"}, parentMessage = "d1.statisticsMerge")
	public static void call() {
    String currentDirectory = System.getProperty("user.dir");
    System.out.println("Current working directory: " + currentDirectory);
		ConfigurationLoader configurationLoader = new ConfigurationLoader("./resources/battleshipConfig.json");
		Battleship battleshipGame = new Battleship();
	}*/

 
}

