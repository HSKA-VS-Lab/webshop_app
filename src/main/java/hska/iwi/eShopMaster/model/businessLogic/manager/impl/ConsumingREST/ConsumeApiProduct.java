package hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST;

import java.util.Optional;

import org.springframework.web.client.RestTemplate;


public class ConsumeApiProduct {

    private String urlApiProduct = "http://zuul:8092/product";
    
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

    public Product[] findProduct(String searchValue, String priceMinValue, String priceMaxValue) {
        String url = buildUrl(searchValue, priceMinValue, priceMaxValue);
        Product[] list = restTemplate.getForObject(url, Product[].class);
        return list;
    }

    String buildUrl(String searchValue, String priceMinValue, String priceMaxValue) {
        String url = urlApiProduct
                + "/find?searchValue=[SVAL]&priceMinValue=[PMIN]&priceMaxValue=[PMAX]";

        if (searchValue != null)
            url = url.replace("[SVAL]", searchValue);
        else
            url = url.replace("searchValue=[SVAL]&", "");

        if (priceMinValue != null)
            url = url.replace("[PMIN]", priceMinValue);
        else
            url = url.replace("priceMinValue=[PMIN]&", "");

        if (priceMaxValue != null)
            url = url.replace("[PMAX]", priceMaxValue);
        else
            url = url.replace("&priceMaxValue=[PMAX]", "");

        return url;
    }

    public void addProduct(String name, double price, int categoryId, String details) {
        restTemplate.postForLocation(urlApiProduct, name, price, categoryId, details);
    }

}