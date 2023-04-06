package com.onedream.xrouter_compiler;

import com.onedream.xrouter_annotation.Route;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

/**
 * @author jdallen
 * @since 2020/9/2
 */
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class XRouterProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationTypes = new LinkedHashSet<>();
        annotationTypes.add(Route.class.getCanonicalName());
        return annotationTypes;
    }


    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        changeRoute(roundEnv.getElementsAnnotatedWith(Route.class));
        return true;
    }

    private void changeRoute(Set<? extends Element> elements) {
        String statement = "";
        for (Element element : elements) {
            if (element.getKind() != ElementKind.CLASS) {
                continue;
            }
            TypeElement typeElement = (TypeElement) element;
            // 获取类的上一级元素，即包元素
            PackageElement packageElement = (PackageElement) typeElement.getEnclosingElement();
            // 获取包名
            String packageName = packageElement.getQualifiedName().toString();
            //
            //
            String key = typeElement.getAnnotation(Route.class).value();
            statement += "\tif(key.equals(\"" + key + "\")){\n" + "\treturn Class.forName(\"" + typeElement.getQualifiedName().toString() + "\");\n} \n";
        }
        statement += "\n return null\n";

        MethodSpec.Builder bindMethodBuilder = MethodSpec.methodBuilder("get")
                .returns(ClassName.get("java.lang", "Class"))
                .addException(ClassName.get("java.lang", "ClassNotFoundException"))
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.STATIC)
                .addParameter(ClassName.get("java.lang", "String"), "key")
                .addStatement(statement);
        //
        TypeSpec.Builder typeBuilder = TypeSpec.classBuilder("XRouterMap")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(bindMethodBuilder.build());
        JavaFile javaFile = JavaFile.builder("com.onedream", typeBuilder.build()).build();

        try {
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}