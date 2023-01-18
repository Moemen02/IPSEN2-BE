package nl.Groep13.OrderHandler.service.V2;

import nl.Groep13.OrderHandler.interfaces.UpdateIncludeAttribute;
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
            UpdateIncludeAttribute include = f.getDeclaredAnnotation(UpdateIncludeAttribute.class);
            if(include != null && isNullOrEmpty(f, include))
                continue;
            Field ff = toFieldNameMap.get(f.getName());
            f.setAccessible(true);
            ff.setAccessible(true);
            ff.set(to, f.get(from));
        }
    }

    private boolean isNullOrEmpty(Field field, UpdateIncludeAttribute annotation) {
        switch (annotation.CheckWhich()) {
            case Null -> {
                return field != null;
            }
            case String -> {
                return field.toString().isEmpty();
            }
            case IsInt -> {
                return field.toString().equals("0");
            }
            case IsFloat -> {
                return field.toString().equals("0.0f");
            }
            case IsLong -> {
                return field.toString().equals("0L");
            }
            default -> { return true;}
        }
    }
}
