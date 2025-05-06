package cn.willow.demo.javaagent;

import java.lang.instrument.Instrumentation;

/**
 * This class is used to transform the class
 *
 * @author willow
 * @date 2024/1/17
 * @see <a href="https://www.baeldung.com/java-instrumentation">https://www.baeldung.com/java-instrumentation</a>
 */
public class AgentClass {
    /**
     * This method is used to transform the class
     *
     * @param agentArgs       The args of the agent
     * @param instrumentation The instrumentation instance
     */
    public static void premain(
            String agentArgs, Instrumentation instrumentation) {

        System.out.println("[Agent] In premain method");
        String className = "cn.willow.demo.javaagent.tester.AgentTester";
        transformClass(className, instrumentation);
    }

    public static void agentmain(
            String agentArgs, Instrumentation instrumentation) {

        System.out.println("[Agent] In agentmain method");
        String className = "cn.willow.demo.javaagent.tester.AgentTester";
        transformClass(className, instrumentation);
    }

    /**
     * This method is used to transform the class
     *
     * @param className       The name of the class to transform
     * @param instrumentation The instrumentation instance
     */
    private static void transformClass(String className, Instrumentation instrumentation) {
        Class<?> targetCls = null;
        ClassLoader targetClassLoader = null;
        // see if we can get the class using forName
        try {
            targetCls = Class.forName(className);
            targetClassLoader = targetCls.getClassLoader();
            transform(targetCls, targetClassLoader, instrumentation);
            return;
        } catch (Exception ex) {
            System.out.println("Class [{}] not found with Class.forName");
        }
        // otherwise iterate all loaded classes and find what we want
        for (Class<?> clazz : instrumentation.getAllLoadedClasses()) {
            if (clazz.getName().equals(className)) {
                targetCls = clazz;
                targetClassLoader = targetCls.getClassLoader();
                transform(targetCls, targetClassLoader, instrumentation);
                return;
            }
        }
        throw new RuntimeException(
                "Failed to find class [" + className + "]");
    }

    /**
     * This method is used to transform the class
     *a
     * @param clazz           The class to transform
     * @param classLoader     The classloader of the class
     * @param instrumentation The instrumentation instance
     */
    private static void transform(
            Class<?> clazz,
            ClassLoader classLoader,
            Instrumentation instrumentation) {
        TesterTransformer dt = new TesterTransformer(clazz.getName(), classLoader);
        instrumentation.addTransformer(dt, true);
        try {
            instrumentation.retransformClasses(clazz);
        } catch (Exception ex) {
            throw new RuntimeException(
                    "Transform failed for: [" + clazz.getName() + "]", ex);
        }
    }
}
