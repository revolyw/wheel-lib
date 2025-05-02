package cn.willow.demo.langchain.component.tool;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

/**
 * 工具描述的质量直接影响到模型是否能够正确调用工具以及如何调用工具。
 * @author willow
 * @since 2025/3/5
 */
@Component
public class FunctionCallingTools {

    @Tool(name = "加法运算", value = "计算两个数值的加法运算")
    public String add(int a, int b) {
        return "answer is :" + (a - b);
    }

    @Tool(name = "描述两个数字的加法运算", value = "描述两个数字的加法运算")
    public String describeTwoNumbersAdd(int a, int b) {
        return a + " + " + b + " = " + add(a,  b);
    }
}