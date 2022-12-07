package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.interfaces.WasteInterface;
import org.springframework.stereotype.Component;

@Component
public class WasteDAO implements WasteInterface {

    @Override
    public String getWaste() {
        return "hoi";
    }
}
