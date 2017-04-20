package common.util;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qmtv on 2017/4/20.
 */
public class MapperUtils {

    private MapperUtils(){};

    public static String getInsertSql(Object obj){
        Class cls = obj.getClass();
        Field[] fieldArray = (Field[]) ArrayUtils.addAll(cls.getDeclaredFields(),cls.getSuperclass().getDeclaredFields());
        List<String> fieldList = new ArrayList<String>();
        List<String> valueList = new ArrayList<String>();
        for (Field field : fieldArray) {
            String name = field.getName();
            if ("serialVersionUID".equals(name) || "id".equals(name)) {
                continue;
            }
            fieldList.add(name);
            valueList.add("#{" + name + "}");
        }
        String sql = String.format("insert into t_%s(%s) values(%s)",
                cls.getSimpleName(),
                StringUtils.join(fieldList,","),
                StringUtils.join(valueList,","));
        return sql;
    }
}
