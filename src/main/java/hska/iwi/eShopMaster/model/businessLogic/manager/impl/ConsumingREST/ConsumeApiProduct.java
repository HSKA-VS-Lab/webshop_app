package hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST;

import java.util.Optional;

import org.springframework.web.client.RestTemplate;


public class ConsumeApiProduct {

    private String urlApiProduct = "http://localhost:8087/product";
    
    RestTemplate restTemplate = new RestTemplate();

    public Product[] getProducts() {
        Product [] products = restTemplate.getForObject(urlApiProduct, Product[].class);
        return products;
    }

    public void deleteProduct(int id) {
        restTemplate.delete(urlApiProduct + "/" + id);
    }

    public void updateProduct(int id, String name, double price, int categoryId, String details) {
        restTemplate.put(urlApiProduct + "/" + id, name, price, categoryId, details);
    }

    public void deleteAllProducts() {
        restTemplate.delete(urlApiProduct);
    }

    public Product getProduct(int id) {
        return restTemplate.getForObject(urlApiProduct + "/" + id, Product.class);
    }

    public Product[] findProduct(Optional<String> searchValue, Optional<String> priceMinValue, Optional<String> priceMaxValue) {
        Product[] list = restTemplate.getForObject(urlApiProduct + "/product/find", Product[].class, searchValue, priceMinValue, priceMaxValue);
        return list;
    }

    public void addProduct(String name, double price, int categoryId, String details) {
        restTemplate.postForLocation(urlApiProduct, name, price, categoryId, details);
    }

}