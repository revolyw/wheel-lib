package cn.willow.demo.javaagent;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

/**
 * This class is used to transform the class
 * @author willow
 * @date 2024/1/17
 */
public class TesterTransformer implements ClassFileTransformer {
    private final String targetClassName;
    private final ClassLoader targetClassLoader;

    public TesterTransformer(String targetClassName, ClassLoader targetClassLoader) {
        this.targetClassName = targetClassName;
        this.targetClassLoader = targetClassLoader;
    }

    /**
     * This method is used to transform the class
     * @param loader                the defining loader of the class to be transformed,
     *                              may be <code>null</code> if the bootstrap loader
     * @param className             the name of the class in the internal form of fully
     *                              qualified class and interface names as defined in
     *                              <i>The Java Virtual Machine Specification</i>.
     *                              For example, <code>"java/util/List"</code>.
     * @param classBeingRedefined   if this is triggered by a redefine or retransform,
     *                              the class being redefined or retransformed;
     *                              if this is a class load, <code>null</code>
     * @param protectionDomain      the protection domain of the class being defined or redefined
     * @param classfileBuffer       the input byte buffer in class file format - must not be modified
     *
     * @return a well-formed class file buffer (the result of the transform),
     */
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        byte[] byteCode = classfileBuffer;
        String finalTargetClassName = this.targetClassName
                .replaceAll("\\.", "/");
        if (!className.equals(finalTargetClassName)) {
            return byteCode;
        }

        if (loader.equals(targetClassLoader)) {

            System.out.println("[Agent] Transforming class TesterTransformer");
            try {
                //use Javassist to add byte-code to target class and add a log
                ClassPool cp = ClassPool.getDefault();
                CtClass cc = cp.get(targetClassName);
                CtMethod m = cc.getDeclaredMethod("method");
                m.insertBefore("System.out.println(\"parameter is \" + parameter);");
                m.insertAfter("System.out.println(\"test method end\");");
                byteCode = cc.toBytecode();
                cc.detach();
            } catch (NotFoundException | CannotCompileException | IOException e) {
                System.out.println("Exception" + e.getMessage());
            }
        }
        return byteCode;
    }
}
