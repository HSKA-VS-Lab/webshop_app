package hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST;

import org.springframework.web.client.RestTemplate;

public class ConsumeApiCategory {

    private String urlApiCategory = "http://zuul:8092/category";
    
    RestTemplate restTemplate = new RestTemplate();

    public Category[] getCategories() {
        Category [] categories = restTemplate.getForObject(urlApiCategory, Category[].class);
        return categories;
    }

    public Category getCategory(int id) {
        Category category = restTemplate.getForObject(urlApiCategory + "/" + id, Category.class);
        return category;
    }

    public void addCategory(String name) {
        restTemplate.postForLocation(urlApiCategory, name);
    }

    public void updateCategory(int id, String name) {
        restTemplate.put(urlApiCategory + "/" + id, name);
    }

    public void deleteCategory(int id) {
        restTemplate.delete(urlApiCategory + "/" + id);
    }

}