package com.example.accessingdatamysql.Store;

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

    @GetMapping("/show_products")
    public SearchAnswer showProducts(@RequestParam Integer keyWordId){
        return storeService.showProducts(keyWordId);
    }
}
