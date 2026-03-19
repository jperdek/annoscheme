package com.crustlab.aop.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ImportedFunctionalityController {

  private final ImportedService importedService;

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @Autowired
  public ImportedFunctionalityController(ImportedService importedService) {
        this.importedService = importedService;
  }

  //@Action(actionType = ActionType.START, message = "d1.startBattleship", diagramIdentifiers = {"d1.id"})
	//@Action(actionType = ActionType.END, message = "d1.endBattleship", diagramIdentifiers = {"d1.id"}, parentMessage = "d1.statisticsMerge")
	public void call() {
    String currentDirectory = System.getProperty("user.dir");
    System.out.println("Current working directory: " + currentDirectory);

		//this.battleship = new Battleship();
    this.importedService.run();
	}

  @GetMapping("/functionalityMeasure")
  public void  greeting(@RequestParam(name="name", defaultValue = "World") String name) {
    System.out.println("Launching application");
    call();
    System.out.println("Terminating application");
  }
  
}

