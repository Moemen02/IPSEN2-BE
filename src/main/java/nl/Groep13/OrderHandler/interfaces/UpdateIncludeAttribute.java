package nl.Groep13.OrderHandler.interfaces;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UpdateIncludeAttribute {

    enum CheckWhich {
        String,
        Null,
        IsInt,
        IsFloat,
        IsLong
    }
    CheckWhich CheckWhich();

}
