package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.DAO.v2.UsageDAO;
import nl.Groep13.OrderHandler.model.v2.ArticleData;
import nl.Groep13.OrderHandler.model.v2.Usage;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v2/usage")
public class UsageController{

    private final UsageDAO usageDAO;

    public UsageController(UsageDAO usageDAO){
        this.usageDAO = usageDAO;
    }

    @GetMapping
    public ResponseEntity<List<Usage>> getAllUsages() {
        return ResponseEntity.ok(
                this.usageDAO.getAllUsages()
        );
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Usage> getUsageById(@PathVariable Long id) {
        try {
            Usage checkedUsage = this.usageDAO.getUsageById(id);
            return new ResponseEntity<>(checkedUsage, HttpStatus.FOUND);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public long setUsageType(ArticleData articleData){
        List<Usage> usageList = this.usageDAO.getAllUsages();
        Float patternLength = articleData.getPatternLength();
        final long[] returningUsageID = {0L};

        //is..when is when article is bigger then value then it gets a usage type
        Float isSafedWhen = 2F;
        Float isRetourWhen = 5F;

        String usageType;

        if (patternLength <= isSafedWhen){
            usageType = "AFVAL";
        } else if (patternLength <= isRetourWhen) {
            usageType = "WASTE";
        } else {
            usageType = "RETOUR";
        }

        usageList.forEach(usage -> {
            if (usage.getType_usage().equals(usageType)){
                returningUsageID[0] = usage.getId();
            }
        });

        return returningUsageID[0];
    }

    public String getUsageType(Long id) throws ChangeSetPersister.NotFoundException {
        Usage usage = this.usageDAO.getUsageById(id);
        return usage.getType_usage();
    }

    @PostMapping
    public ResponseEntity<Usage> addUsage(@RequestBody Usage usage) {
        if (this.usageDAO.addUsage(usage) == null) {
            throw new NullPointerException("Usage is empty!");
        } else {
            return ResponseEntity.ok(usage);
        }
    }
}
