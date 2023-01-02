package nl.Groep13.OrderHandler.service.V2;

import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Service
public class AttrCopy {
    public void copyAttributes(Object from, Object to) throws IllegalAccessException {
        Map<String, Field> toFieldNameMap = new HashMap<>();
        for(Field f : to.getClass().getDeclaredFields()) {
            toFieldNameMap.put(f.getName(), f);
        }
        for(Field f : from.getClass().getDeclaredFields()) {
            Field ff = toFieldNameMap.get(f.getName());
            f.setAccessible(true);
            if(ff != null && !ff.toString().equals("") && !ff.toString().equals("0") && ff.getType().equals(f.getType())) {
                ff.setAccessible(true);
                ff.set(to, f.get(from));
            }
        }
    }
}
