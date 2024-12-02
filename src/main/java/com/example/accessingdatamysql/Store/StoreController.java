package com.example.accessingdatamysql.Store;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping("/save")
    @Operation(summary = "물건 정보 저장", description = "Parameter로 해당 물건의 정보를 받고 해당 물건을 keywordId로 분류해서 저장")
    public String saveProducts(@RequestBody SearchAnswer request, @RequestParam Integer keywordId){
        return storeService.saveProducts(request, keywordId);
    }

    @GetMapping("/show_products_by_keyword")
    @Operation(summary = "keyword 카테고리별 물건들을 보여줌", description = "Parameter로 keywordId를 받아서 db로부터 해당하는 물건들을 가져온다")
    public SearchAnswer showProducts(@RequestParam Integer keyWordId){
        return storeService.showProducts(keyWordId);
    }

    @GetMapping("/show_all_products")
    @Operation(summary = "모든 물건을 보여줌")
    public SearchAnswer showAllProducts(){
        return storeService.showProducts();
    }
}
