package org.annoscheme.common.model.element;

import org.annoscheme.common.annotation.ActionType;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static org.annoscheme.common.model.constants.PlantUmlConstants.DIAGRAM_END;
import static org.annoscheme.common.model.constants.PlantUmlConstants.DIAGRAM_START;

public class ActivityDiagramElement extends DiagramElement {

	private String message;

	private String parentMessage;

	private ActionType actionType = ActionType.ACTION;

	private String[] diagramIdentifiers = new String[]{};

	@Override
	@JsonIgnore
	public String toPlantUmlString() {
		//TODO check for very long messages and split them by certain character count
		StringBuilder plantUmlStringBuilder = new StringBuilder(getPlantUmlElementMessage());
		if (ActionType.START.equals(actionType)) {
			plantUmlStringBuilder.insert(0, DIAGRAM_START);
		} else if (ActionType.END.equals(actionType)) {
			plantUmlStringBuilder.append(DIAGRAM_END);
		} if (ActionType.VOID.equals(actionType)) {
			return "";
		}
		return plantUmlStringBuilder.toString();
	}

	@JsonIgnore
	public String getPlantUmlElementMessage() {
		StringBuilder plantUmlMessage = new StringBuilder("");
		if (message != null && "".equals(message)) {
			return plantUmlMessage.toString();
		}
		plantUmlMessage.append(":").append(trimAndReplaceQuotes(message));
		plantUmlMessage.append(";\n");
		return plantUmlMessage.toString();
	}

	public String getMessage() {
		if (message == null) { message = "Undefined. Probably caused by no value for message of certain action in annotationvalue.properties file!"; }
		return message;
	}

	public void setMessage(String message) {
		this.message = trimAndReplaceQuotes(message);
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public String[] getDiagramIdentifiers() {
		return diagramIdentifiers;
	}

	public void setDiagramIdentifiers(String[] diagramIdentifiers) {
		this.diagramIdentifiers = diagramIdentifiers != null ? Arrays.stream(diagramIdentifiers)
										.map(this::trimAndReplaceQuotes)
										.toArray(String[]::new) : null;

	}

	public String getParentMessage() {
		return parentMessage != null ? parentMessage: "";
	}

	public void setParentMessage(String parentMessage) {
		this.parentMessage = trimAndReplaceQuotes(parentMessage);
	}

}
