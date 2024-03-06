package cn.willow.demo.javaagent.tester;

/**
 * This class is used to test the agent
 * @author willow
 * @date 2024/1/17
 */
public class AgentTester {
    /**
     * This method is used to test the agent
     * @param args The args of the method
     */
    public static void main(String[] args) {
        String helloWorld = method("hello world");
    }
    /**
     * This method is used to test the agent
     * @param parameter The parameter of the method
     * @return The parameter of the method
     */
    public static String method(String parameter) {
        System.out.println(parameter);
        return parameter;
    }
}
