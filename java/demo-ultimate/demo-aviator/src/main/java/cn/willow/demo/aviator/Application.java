package cn.willow.demo.aviator;


import com.alibaba.fastjson.JSON;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    @Data
    public static class Property {
        private String code;
        private String name;
        private List<String> expressionChains;
    }

    /**
     * [
     *     {
     *         "code": "psLt3",
     *         "name": "PS<3",
     *         "expressionChains": [
     *             "bigint(ps) < 3 ? 1 : 0"
     *         ]
     *     },
     *     {
     *         "code": "mammaryHer2Yang",
     *         "name": "Her-2阳性",
     *         "expressionChains": [
     *             "str(mammaryHer2) == str(0) || str(mammaryHer2) == str(1) ? 0 : nil",
     *             "str(mammaryHer2) == str(3) ? 1 : nil",
     *             "str(mammaryHer2) == str(2) && str(mammaryFISH) == str(1) ? 1 : 0"
     *         ]
     *     }
     * ]
     */
    public static final String properties = "[{\"code\":\"psLt3\",\"name\":\"PS<3\",\"expressionChains\":[\"bigint(ps) < 3 ? 1 : 0\"]},{\"code\":\"mammaryHer2Yang\",\"name\":\"Her-2阳性\",\"expressionChains\":[\"str(mammaryHer2) == str(0) || str(mammaryHer2) == str(1) ? 0 : nil\",\"str(mammaryHer2) == str(3) ? 1 : nil\",\"str(mammaryHer2) == str(2) && str(mammaryFISH) == str(1) ? 1 : 0\"]}]";
    public static void main(String[] args) {
        List<Property> tags = JSON.parseArray(properties, Property.class);
        Map<String, Object> tagMap = new HashMap<>();
        tagMap.put("ps", "2");
        tagMap.put("mammaryHer2", "2");
        tagMap.put("mammaryFISH", "1");
        tags.forEach(o -> {
            List<String> expressionChains = o.getExpressionChains();
            expressionChains.forEach(e->{
                //解析每一个表达式
                Expression expression = AviatorEvaluator.getInstance().compile(e);
                //提取表达式中的变量
                List<String> variableNames = expression.getVariableNames();
                System.out.println(variableNames);
                variableNames.forEach(v->{
                    if (tagMap.containsKey(v)) {
                        //从环境变量中提取自己需要的变量值并执行，多余的值会被忽略。
                        Object result = expression.execute(tagMap);
                        if (null == result) {
                            System.out.println("标签缺失：" + v);
                        } else {
                            System.out.println(o.getCode() + "->" + result);
                        }
                    }
                });
            });
        });
        System.out.println(tagMap);
    }

}

