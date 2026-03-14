package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.aspects.interception.DiagramCoordinationCommand;

import battleship.Battleship;
import configurationManagement.ConfigurationLoader;
import org.annoscheme.common.annotation.Action;
import org.annoscheme.common.annotation.ActionType;


@RestController
public class FunctionalityMeasureController {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @Action(actionType = ActionType.START, message = "d1.startBattleship", diagramIdentifiers = {"d1.id"})
	@Action(actionType = ActionType.END, message = "d1.endBattleship", diagramIdentifiers = {"d1.id"}, parentMessage = "d1.statisticsMerge")
	public static void call() {
    String currentDirectory = System.getProperty("user.dir");
    System.out.println("Current working directory: " + currentDirectory);
		ConfigurationLoader configurationLoader = new ConfigurationLoader("./resources/battleshipConfig.json");
		Battleship battleshipGame = new Battleship();
	}

  @GetMapping("/staticCreation")
  public String staticCreation(@RequestParam(name="name", defaultValue = "World") String name) {
    System.out.println("Launching application");
    DiagramCoordinationCommand diagramCoordinationCommand = new DiagramCoordinationCommand();
    diagramCoordinationCommand.initializeStaticPhaseDiagram();
    System.out.println("Terminating application");
    return "Greetings from Spring Boot!";
  }

  @GetMapping("/functionalityMeasure")
  public void  greeting(@RequestParam(name="name", defaultValue = "World") String name) {
    System.out.println("Launching application");
    FunctionalityMeasureController.call();
    System.out.println("Terminating application");
  }
}

