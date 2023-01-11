package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.interfaces.LabelInterface;
import nl.Groep13.OrderHandler.model.v2.LabelV2;
import nl.Groep13.OrderHandler.repository.v2.LabelRepositoryV2;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LabelDAOV2 implements LabelInterface {

    private final LabelRepositoryV2 labelRepositoryV2;

    public LabelDAOV2(LabelRepositoryV2 labelRepositoryV2) {
        this.labelRepositoryV2 = labelRepositoryV2;
    }

    @Override
    public List<LabelV2> getLabel(){return labelRepositoryV2.findAll();}

    @Override
    public Optional<LabelV2> getLabelById(final Long id){
        Optional<LabelV2> labelV2 = labelRepositoryV2.findById(id);
        if (labelV2.isPresent()){
            return labelV2;
        }
        return labelV2;
    }

}
