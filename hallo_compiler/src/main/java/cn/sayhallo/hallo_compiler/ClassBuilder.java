package cn.sayhallo.hallo_compiler;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

public class ClassBuilder {

    //包名
    public String packageName ;
    //类名
    public String className ;
    //类节点
    public TypeElement mTypeElement;
    //用于存放BindView标注的Field字段 1 - 1
    public Map<Integer, VariableElement> idAndFdieldNames = new HashMap<>();
    //用于存放OnClick标注的Method字段 1 - n
    public Map<ExecutableElement, int[]> clickMethodNameAndIds = new HashMap<>();

    public JavaFile buildJavaFile() {

        ClassName activity = ClassName.bestGuess(mTypeElement.getQualifiedName().toString());
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("bind")
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .addParameter(activity, "activity",Modifier.FINAL);

        for (int id : idAndFdieldNames.keySet()) {
            VariableElement element = idAndFdieldNames.get(id);
            String fieldName = element.getSimpleName().toString();
            String fieldType = element.asType().toString();
            methodBuilder.addCode("activity." + fieldName + " = " + "(" + fieldType + ")(((android.app.Activity)activity).findViewById( " + id + "));\n");
        }

        for (ExecutableElement element : clickMethodNameAndIds.keySet()) {
            String methodName = element.getSimpleName().toString();
            int[] ids = clickMethodNameAndIds.get(element);
            for (int id : ids) {
                VariableElement variableElement = idAndFdieldNames.get(id);
                if ( variableElement != null) {
                    String fieldName = variableElement.getSimpleName().toString();
                    methodBuilder.addCode("activity." + fieldName + ".setOnClickListener(new android.view.View.OnClickListener() {\n" +
                            "      @Override\n" +
                            "      public void onClick(android.view.View v) {\n" +
                            "        activity. " + methodName + "(v); \n" +
                            "      }\n" +
                            "    });");
                }
            }
        }

        TypeSpec helloWorld = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .addMethod(methodBuilder.build())
                .build();

        return JavaFile.builder(packageName, helloWorld)
                .build();
    }
}
