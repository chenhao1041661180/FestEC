package com.rocky.compiler;

import com.google.auto.service.AutoService;
import com.rocky.annotations.AppRegisterGenerator;
import com.rocky.annotations.EntryGenerator;
import com.rocky.annotations.PayEntryGenerator;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/26
 */
@SuppressWarnings("unchecked")
//@SuppressWarnings({ "rawtypes", "unchecked" })
@AutoService(Processor.class)
public final class LatteProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        genrateEntryCode(roundEnvironment);
        genratePayEntryCode(roundEnvironment);
        genrateAppRegisterCode(roundEnvironment);
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> types = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> supportAnnotation = getSupportedAnnotation();
        for (Class<? extends Annotation> annotations : supportAnnotation) {
            types.add(annotations.getCanonicalName());
        }
        return types;
    }

    private Set<Class<? extends Annotation>> getSupportedAnnotation() {
        final Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(EntryGenerator.class);
        annotations.add(PayEntryGenerator.class);
        annotations.add(AppRegisterGenerator.class);
        return annotations;
    }

    private void scan(RoundEnvironment env, Class<? extends Annotation> annotation, AnnotationValueVisitor visitor) {
//        for (Element typeElement : evn.getElementsAnnotatedWith(annotation)) {
//            final List<? extends AnnotationMirror> annotationMirrors = typeElement.getAnnotationMirrors();
//
//            for (AnnotationMirror annotationMirror : annotationMirrors) {
//                final Map<? extends ExecutableElement, ? extends AnnotationValue> elementValue = annotationMirror.getElementValues();
//                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : elementValue.entrySet()) {
//                    entry.getValue().accept(visitor, null);
//                }
//            }
//        }
        for (Element typeElement : env.getElementsAnnotatedWith(annotation)) {
            final List<? extends AnnotationMirror> annotationMirrors =
                    typeElement.getAnnotationMirrors();

            for (AnnotationMirror annotationMirror : annotationMirrors) {
                final Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues
                        = annotationMirror.getElementValues();

                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry
                        : elementValues.entrySet()) {
                    entry.getValue().accept(visitor, null);
                }
            }
        }
    }

    private void genrateEntryCode(RoundEnvironment env) {
        EntryVisitor entryVisitor = new EntryVisitor(processingEnv.getFiler());
        scan(env, EntryGenerator.class, entryVisitor);
    }

    private void genratePayEntryCode(RoundEnvironment env) {
        PayEntryVisitor payEntryVisitor = new PayEntryVisitor(processingEnv.getFiler());
        scan(env, PayEntryGenerator.class, payEntryVisitor);
    }

    private void genrateAppRegisterCode(RoundEnvironment env) {
        AppRegisterVisitor appRegisterVisitor = new AppRegisterVisitor(processingEnv.getFiler());
        scan(env, AppRegisterGenerator.class, appRegisterVisitor);
    }
}
