package org.annoscheme.common.model;

import org.annoscheme.common.annotation.ActionType;
import org.annoscheme.common.model.element.ActivityDiagramElement;
import org.annoscheme.common.model.element.ConditionalActivityDiagramElement;
import org.annoscheme.common.model.element.JoiningDiagramElement;
import org.annoscheme.common.model.element.ObjectActivityDiagramElement;
import org.annoscheme.common.model.element.PlantUmlIntegrable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static org.annoscheme.common.model.constants.PlantUmlConstants.END_UML;
import static org.annoscheme.common.model.constants.PlantUmlConstants.START_UML;
import java.util.Iterator;

public class ActivityDiagramModel implements PlantUmlIntegrable {

	private String diagramIdentifier;

	private List<ActivityDiagramElement> activityDiagramElements = new ArrayList<>();

	//TODO toPlantUmlString with this builder
	@JsonIgnore
	private StringBuilder plantUmlStringBuilder;

	@Override
	@JsonIgnore
	public String toPlantUmlString() {
		StringBuilder plantUmlStringBuilder = new StringBuilder();
		ActivityDiagramElement startElement = activityDiagramElements.stream().filter(x -> x.getActionType().equals(ActionType.START)).findFirst().orElse(null);
		if (startElement == null) {
			throw new IllegalStateException("Diagram has no starting element");
		}
		plantUmlStringBuilder.append(START_UML);
		plantUmlStringBuilder.append(startElement.toPlantUmlString());
		Iterator<ActivityDiagramElement> i = this.activityDiagramElements.iterator();
		while (i.hasNext()) {
			ActivityDiagramElement a = i.next();
			System.out.println(a.getActionType() + " "+ a.getParentMessage());
		}
		ActivityDiagramElement current = startElement;
		boolean reachedEndState = false;
		//TODO sentinel the processing
		while (!reachedEndState) {
			if (current != null) {
				ActivityDiagramElement finalCurrent = current;
				String activityNameToProcess = current.getMessage();
				ActionType actionType = current.getActionType();
				//find next element to process, assign it to 'current'
				if (current instanceof ConditionalActivityDiagramElement) {
					current = activityDiagramElements
							.stream()
							.filter(x -> x instanceof JoiningDiagramElement &&
										 x.getParentMessage().equals(((ConditionalActivityDiagramElement) finalCurrent).getCondition()))
							.findFirst().orElse(null);
					if (current == null) { 
						throw new IllegalStateException("Error for ConditionalActivityDiagramElement! Parent message from asociated action is wrongly specified!" + 
								activityNameToProcess + "< with type: >" + actionType.toString() + "<..."); 
					}
				} else {
					current = activityDiagramElements.stream().filter(x -> x.getParentMessage() != null && x.getParentMessage().equals(finalCurrent.getMessage()))
													 .findFirst().orElse(null);
					if (current == null) { 
						throw new IllegalStateException("Error for Action! Parent messages are not placed properly. No parent is found for >" + 
								activityNameToProcess + "< with type: >" + actionType.toString() + "<..."); 
					}
				}
				if (current instanceof ConditionalActivityDiagramElement) {
					
					ConditionalActivityDiagramElement currentConditional = (ConditionalActivityDiagramElement) current;
			
					plantUmlStringBuilder.append("if (")
										 .append(currentConditional.getCondition())
										 .append(") ")
										 .append("then ").append("([" + currentConditional.getTrueClause() + "]) \n");
					//get main branch
					ActivityDiagramElement mainFlowDirect = currentConditional.getMainFlowDirectChild();
					if (mainFlowDirect == null) { 
						throw new IllegalStateException("Error for MainFlow Cond! Parent messages are not placed properly. No parent is found for >" + 
								activityNameToProcess + "< with type: >" + actionType.toString() + "<..."); 
					}
					plantUmlStringBuilder.append(this.getPlantUmlConditionalBranch(mainFlowDirect));
					plantUmlStringBuilder.append("else ([").append(currentConditional.getFalseClause()).append("]) \n");
					//get alternative branch
					plantUmlStringBuilder.append(this.getPlantUmlConditionalBranch(currentConditional.getAlternateFlowDirectChild()));
				} else {
					if (current != null) {
						plantUmlStringBuilder.append(current.toPlantUmlString());
					} else {
						throw new IllegalStateException("Diagram element is ommited. Parent messages are not placed properly...");
					}
				}
				if (current.getActionType().equals(ActionType.END)) {
					reachedEndState = true;
				}
			} else {
				throw new IllegalStateException("Cannot reach end state");
			}
		}
		//System.out.println(plantUmlStringBuilder.toString());
		//if (true) { throw new IllegalStateException("DONE"); }
		plantUmlStringBuilder.append(END_UML);
		return plantUmlStringBuilder.toString();
	}

	private String getPlantUmlConditionalBranch(ActivityDiagramElement fromElement) {
		StringBuilder plantUmlStringBuilder = new StringBuilder();
		ActivityDiagramElement current = fromElement;
		plantUmlStringBuilder.append(current.toPlantUmlString());
		while (current != null && !current.getActionType().equals(ActionType.END)) {
			System.out.println("Condition making....");
			ActivityDiagramElement finalCurrent = current;
			ActivityDiagramElement child = activityDiagramElements.stream()
																  .filter(x -> x.getParentMessage() != null &&
																			   x.getParentMessage().equalsIgnoreCase(finalCurrent.getMessage().toLowerCase()))
																  .findFirst()
																  .orElse(null);
			if (child != null) {
				plantUmlStringBuilder.append(child.toPlantUmlString());
			}
			current = child;
		}
		return plantUmlStringBuilder.toString();
	}

	public void addElement(ActivityDiagramElement element) {
		List<ActivityDiagramElement> sortedElements = this.getDiagramElements();
		//find parent if not empty, then indexOf parent -> add after parent
		if (sortedElements.isEmpty()) {
			sortedElements.add(element);
			return;
		}
		if (ActionType.START.equals(element.getActionType())) {
			String message = element.getParentMessage();
			if (message != null || !"".equals(message) && sortedElements.stream().noneMatch(e -> e.getActionType().equals(ActionType.START))) {
				sortedElements.add(0, element);
				return;
			} else {
				throw new IllegalArgumentException("Diagram already contains start node");
			}
		}

		ConditionalActivityDiagramElement conditionalElement;
		if (element instanceof ConditionalActivityDiagramElement) {
			conditionalElement = (ConditionalActivityDiagramElement) element;
			//lookup if conditional with same condition does not exist already, if yes update with new branch
			Optional<ActivityDiagramElement> existingConditionalOptional = sortedElements
					.stream()
					.filter(x -> x instanceof ConditionalActivityDiagramElement &&
								 ((ConditionalActivityDiagramElement) x).getCondition().equalsIgnoreCase(conditionalElement.getCondition().toLowerCase()) &&
								 x.getParentMessage().equalsIgnoreCase(conditionalElement.getParentMessage().toLowerCase()))
					.findFirst();
			//if existing, update it's branches
			if (existingConditionalOptional.isPresent()) {
				//there already is existing conditional with same condition -> update main/alt branch
				ConditionalActivityDiagramElement existingConditional = (ConditionalActivityDiagramElement) existingConditionalOptional.get();
				if (conditionalElement.getAlternateFlowDirectChild() != null && existingConditional.getAlternateFlowDirectChild() == null) {
					existingConditional.setAlternateFlowDirectChild(conditionalElement.getAlternateFlowDirectChild());
				} else {
					existingConditional.setMainFlowDirectChild(conditionalElement.getMainFlowDirectChild());
				}
				return;
			} else {
				//no existing conditional, create new & add branch
			}
		}
		//find parent
		Optional<ActivityDiagramElement> parentElement = sortedElements.stream()
																	   .filter(x -> x.getMessage().equalsIgnoreCase(element.getParentMessage().toLowerCase()))
																	   .findFirst();

		if (parentElement.isPresent()) {
			ActivityDiagramElement foundParentElement = parentElement.get();
			sortedElements.add(sortedElements.indexOf(foundParentElement) + 1, element);
		} else {
			sortedElements.add(element);
		}

		setDiagramElements(sortedElements);
	}

	public ActivityDiagramModel() {
	}

	public ActivityDiagramModel(String diagramIdentifier, List<ActivityDiagramElement> activityDiagramElements) {
		this.diagramIdentifier = diagramIdentifier;
		this.activityDiagramElements = activityDiagramElements;
	}

	public ActivityDiagramModel(ActivityDiagramModel model) {
		this.activityDiagramElements = model.getDiagramElements();
		this.diagramIdentifier = model.getDiagramIdentifier();
	}

	public ActivityDiagramModel(String diagramIdentifier) {
		this.diagramIdentifier = diagramIdentifier;
	}

	public String getDiagramIdentifier() {
		return diagramIdentifier;
	}

	public void setDiagramIdentifier(String diagramIdentifier) {
		this.diagramIdentifier = diagramIdentifier;
	}

	public List<ActivityDiagramElement> getDiagramElements() {
		return activityDiagramElements;
	}

	public void setDiagramElements(List<ActivityDiagramElement> activityDiagramElements) {
		this.activityDiagramElements = activityDiagramElements;
	}

	public void removeObjectElements() {
		List<ActivityDiagramElement> elementsToRemove = new ArrayList<>();
		this.getDiagramElements().stream()
			.filter(element -> element instanceof ObjectActivityDiagramElement)
			.forEach(diagramElement -> {
				ObjectActivityDiagramElement objectDiagramElement = (ObjectActivityDiagramElement) diagramElement;
				ActivityDiagramElement predecessor = this.findDiagramElementByMessage(objectDiagramElement.getParentMessage());
				ActivityDiagramElement successor = this.findDiagramElementByParentMessage(objectDiagramElement.getMessage());
				if (predecessor != null) {
					if (ActionType.END.equals(objectDiagramElement.getActionType())) {
						predecessor.setActionType(ActionType.END);
					} else if (successor != null) {
						successor.setParentMessage(predecessor.getMessage());
					}
				}
				elementsToRemove.add(objectDiagramElement);
			});
		this.getDiagramElements().removeAll(elementsToRemove);
	}

	private ActivityDiagramElement findDiagramElementByMessage(String message) {
		Optional<ActivityDiagramElement> elementOptional = this.getDiagramElements().stream()
															   .filter(element -> element.getMessage().equals(message)).findFirst();
		return elementOptional.orElse(null);
	}

	private ActivityDiagramElement findDiagramElementByParentMessage(String parentMessage) {
		Optional<ActivityDiagramElement> elementOptional = this.getDiagramElements().stream()
															   .filter(element -> element.getParentMessage().equals(parentMessage)).findFirst();
		return elementOptional.orElse(null);
	}
}
