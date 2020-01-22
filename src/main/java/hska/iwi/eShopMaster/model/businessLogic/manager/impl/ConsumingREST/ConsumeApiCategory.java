package hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class ConsumeApiCategory {

    private static final Logger log = LoggerFactory.getLogger(ConsumeApiCategory.class);
    private String urlApiCategory = "http://zuul:8092/category";
    
    RestTemplate restTemplate = new RestTemplate();

    public Category[] getCategories() {
        log.info("getCategories - ConsumeApiCategory - called");
        Category [] categories = restTemplate.getForObject(urlApiCategory, Category[].class);
        log.info("getCategories - ConsumeApiCategory - RESULT length: "+categories.length);
        return categories;
    }

    public Category getCategory(int id) {
        Category category = restTemplate.getForObject(urlApiCategory + "/" + id, Category.class);
        return category;
    }

    public void addCategory(Category category) {
        restTemplate.postForLocation(urlApiCategory, category);
    }

    public void updateCategory(Category category) {
        restTemplate.put(urlApiCategory, category);
    }

    public void deleteCategory(int id) {
        restTemplate.delete(urlApiCategory + "/" + id);
    }

}