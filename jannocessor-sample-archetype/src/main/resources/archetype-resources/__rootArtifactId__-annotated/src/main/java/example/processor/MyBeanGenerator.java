package example.processor;

import org.jannocessor.collection.api.PowerList;
import org.jannocessor.model.executable.JavaMethod;
import org.jannocessor.model.structure.JavaClass;
import org.jannocessor.model.util.Fields;
import org.jannocessor.model.util.Methods;
import org.jannocessor.model.util.New;
import org.jannocessor.model.variable.JavaField;
import org.jannocessor.processor.context.CodeProcessor;
import org.jannocessor.processor.context.ProcessingContext;

public class MyBeanGenerator implements CodeProcessor<JavaClass> {

	private final boolean debugMode;

	public MyBeanGenerator(boolean debugMode) {
		this.debugMode = debugMode;
	}

	@Override
	public void process(PowerList<JavaClass> classes, ProcessingContext context) {
		context.getLogger().info("Processing {} classes", classes.size());

		for (JavaClass clazz : classes) {
			clazz.getName().appendPart("Gen");
			clazz.getMetadata().clear();

			for (JavaField field : clazz.getFields()) {
				field.getModifiers().assign(Fields.PRIVATE);

				String getterName = field.getName().copy().insertPart(0, "get").toString();
				JavaMethod getter = New.method(Methods.PUBLIC, field.getType(), getterName);
				getter.getBody().setHardcoded(String.format("return this.%s;", field.getName()));

				clazz.getMethods().add(getter);
			}

			context.generateCode(clazz, debugMode);
		}
	}

}