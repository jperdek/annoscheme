package org.annoscheme.common.model.element;

import org.annoscheme.common.annotation.ActionType;
import org.annoscheme.common.annotation.BranchingType;

public class ConditionalActivityDiagramElement extends ActivityDiagramElement {

	private String condition;

	private BranchingType branchingType;

	private ActivityDiagramElement mainFlowDirectChild;

	private ActivityDiagramElement alternateFlowDirectChild;
	
	private String trueClause = "true";
	
	private String falseClause = "false";
	
	
	public ActionType getActionType() {
		return ActionType.CONDITIONAL;
	}

	public void setTrueClause(String trueClause) {
		if (trueClause == null) { trueClause = "true"; }
		this.trueClause = trueClause;
	}
	
	public void setFalseClause(String falseClause) {
		if (falseClause == null) { falseClause = "false"; }
		this.falseClause = falseClause;
	}
	
	public String getTrueClause() {
		return this.trueClause;
	}
	
	public String getFalseClause() {
		return this.falseClause;
	}
	
	public ActivityDiagramElement getMainFlowDirectChild() {
		return mainFlowDirectChild;
	}

	public void setMainFlowDirectChild(ActivityDiagramElement mainFlowDirectChild) {
		this.mainFlowDirectChild = mainFlowDirectChild;
	}

	public ActivityDiagramElement getAlternateFlowDirectChild() {
		return alternateFlowDirectChild;
	}

	public void setAlternateFlowDirectChild(ActivityDiagramElement alternateFlowDirectChild) {
		this.alternateFlowDirectChild = alternateFlowDirectChild;
	}

	public String getCondition() {
		return trimAndReplaceQuotes(condition);
	}

	public void setCondition(String condition) {
		this.condition = trimAndReplaceQuotes(condition);
	}

	public BranchingType getBranchingType() {
		return branchingType;
	}

	public void setBranchingType(BranchingType branchingType) {
		this.branchingType = branchingType;
	}
}
