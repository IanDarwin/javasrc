package oo;

import java.util.function.Consumer;

/**
 * An alternate Builder mechanism, depending on mutable objects, taken from
 * https://glaforge.dev/posts/2024/01/16/java-functional-builder-approach/
 * @author Original by Guillame LaForge
 */
class BuilderAlternate {
	private String modelName;
	private Double temperature = 0.3;
	private Integer maxTokens = 100;

	/** ModelOption is a synonym for a Consumer<BuilderAlternate> */
	public interface ModelOption extends Consumer<BuilderAlternate> {}

	public BuilderAlternate(ModelOption... options) {
		for (ModelOption option : options) {
			option.accept(this);
		}
	}

	public String toString() {
		return "BuilderAlternate(name = " + modelName +
			", temp = " + temperature + "°C, maxOut = " + maxTokens + ")";
	}

	/** Utility methods that update the model instance */

	public static ModelOption modelName(String modelName) {
		return model -> model.modelName = modelName;
	}

	public static ModelOption temperature(Double temperature) {
		return model -> model.temperature = temperature;
	}

	public static ModelOption maxTokens(Integer maxTokens) {
		return model -> model.maxTokens = maxTokens;
	}

	public static void main(String[] args) { 
		BuilderAlternate m = new BuilderAlternate(
			modelName("Toronto"),
			temperature(-32d),
			maxTokens(123)
		);
		System.out.println(m);
	}
}
