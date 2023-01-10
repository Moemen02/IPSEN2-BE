package nl.Groep13.OrderHandler.service.V2;

import nl.Groep13.OrderHandler.DAO.v2.CategoryWasteDAO;
import nl.Groep13.OrderHandler.DAO.v2.WasteDataDAO;
import nl.Groep13.OrderHandler.model.v2.Category;
import nl.Groep13.OrderHandler.model.v2.Composition;
import nl.Groep13.OrderHandler.model.v2.WasteData;
import nl.Groep13.OrderHandler.record.CompositionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class CategoryWasteService {
    @Autowired
    private CategoryWasteDAO categoryWasteDAO;
    @Autowired
    private WasteDataDAO wasteDataDAO;

    public List<Category> calcAllWaste(CompositionRequest compositionRequest) {
        List<Object> wasteData = wasteDataDAO.getAllWasteDataInStock(compositionRequest.stockType());
        List<Category> similarWaste = new ArrayList<>();

        for (int i = 0; i < wasteData.size(); i++) {
            Object[] cat = (Object[]) wasteData.get(i);
            String[] composition = String.valueOf(cat[2]).split("/");
            if (compare(compositionRequest.conditions(), composition)) {
                if (compositionRequest.color().toString().equals(String.valueOf(cat[1]))) {
                    Category finalCat = new Category(
                            cat[0].toString(),
                            cat[1].toString(),
                            cat[2].toString(),
                            cat[3].toString(),
                            cat[4].toString(),
                            cat[5].toString(),
                            Integer.valueOf(cat[6].toString())
                    );
                    similarWaste.add(finalCat);
                }
            }
        }


        return similarWaste;
    }

    public List<String> getDistinctByColor() {
        return categoryWasteDAO.getDistinctByColor();
    }

    private boolean compare(List<Composition> rules, String[] comp) {
        ArrayList<Boolean> same = new ArrayList<>();
        for (Composition r : rules) {
            for (String c : comp) {
                String[] amountAndType = c.split("%");
                amountAndType[0] = amountAndType[0].trim();
                amountAndType[1] = amountAndType[1].trim();
                switch (r.getCondition()) {
                    case "SameThan":
                        if (r.getAmount() == Integer.valueOf(amountAndType[0])) {
                            if (r.getType().equals(amountAndType[1])) {
                                same.add(true);
                            }
                        }
                        break;
                    case "SmallerThan":
                        if (r.getAmount() > Integer.valueOf(amountAndType[0])) {
                            if (r.getType().equals(amountAndType[1])) {
                                same.add(true);
                            }
                        }
                        break;
                    case "GreaterThan":
                        if (r.getAmount() < Integer.valueOf(amountAndType[0])) {
                            if (r.getType().equals(amountAndType[1])) {
                                same.add(true);
                            }
                        }
                        break;
                }
            }
        }

        return same.size() == rules.size();
    }
}
