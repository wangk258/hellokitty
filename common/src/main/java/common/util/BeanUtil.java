package common.util;

import org.apache.commons.lang.ArrayUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qmtv on 2017/4/20.
 */
public class BeanUtil {

    private BeanUtil(){};

    public static Map<String,Object> beanToMap(Object obj) throws Exception{
        Map<String,Object> map = new HashMap<String, Object>();
        Class cls = obj.getClass();
        Field[] fields = (Field[]) ArrayUtils.addAll(cls.getDeclaredFields(),cls.getSuperclass().getDeclaredFields());
        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }
}
