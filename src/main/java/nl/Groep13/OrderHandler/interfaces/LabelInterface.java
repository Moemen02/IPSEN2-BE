package nl.Groep13.OrderHandler.interfaces;


import nl.Groep13.OrderHandler.model.v2.LabelV2;
import java.util.List;
import java.util.Optional;

public interface LabelInterface {

    List<LabelV2> getLabel();

    Optional<LabelV2> getLabelById(Long id);

}
