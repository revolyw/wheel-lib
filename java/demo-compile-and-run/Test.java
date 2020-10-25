import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Test
{
    public static void main (String[] args) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();

        Class hello = Hello.class;
        Field[] fields = hello.getFields();
        for (Field field:fields) {
            String fieldName = field.getName();
            Class fieldType = field.getType();

            if (fieldType == String.class) {
                result.put(fieldName, "小王");
            }
            if (fieldType == Integer.class || fieldType == int.class) {
                result.put(fieldName, 1);
            }
        }
        System.out.println(result);
        // 输出json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(result);
        System.out.println(json);
    }
}

class Hello
{
    public String name = "小王";
    public int age = 17;
    public int high = 180;
    public float weight = 140.5f;
}
