package edu.cmu.cs.stage3.alice.core.visualization;

public class ArrayOfModelsVisualization extends CollectionOfModelsVisualization {
	protected String getItemsName() {
		return java.util.ResourceBundle.getBundle("edu/cmu/cs/stage3/alice/core/visualization/ArrayOfModelsVisualization").getString("elements");
	}
}
