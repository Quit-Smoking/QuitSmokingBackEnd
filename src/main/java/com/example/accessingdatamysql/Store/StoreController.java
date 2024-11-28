package com.example.accessingdatamysql.Store;

import com.example.accessingdatamysql.CoupangCrawling.SearchAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping("/save")
    public String saveProducts(@RequestBody SearchAnswer request, @RequestParam Integer keywordId){
        return storeService.saveProducts(request, keywordId);
    }
}
