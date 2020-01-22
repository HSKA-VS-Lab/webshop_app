package hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST;

import java.util.Optional;

import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ProductManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;


public class ConsumeApiProduct {

    private static final Logger log = LoggerFactory.getLogger(ConsumeApiProduct.class);

    private String urlApiProduct = "http://zuul:8092/product";
    
    RestTemplate restTemplate = new RestTemplate();

    public Product[] getProducts() {
        Product [] products = restTemplate.getForObject(urlApiProduct, Product[].class);
        return products;
    }

    public void deleteProduct(int id) {
        restTemplate.delete(urlApiProduct + "/" + id);
    }

    public void updateProduct(Product product) {
        restTemplate.put(urlApiProduct, product);
    }

    public void deleteAllProducts() {
        restTemplate.delete(urlApiProduct);
    }

    public Product getProduct(int id) {
        return restTemplate.getForObject(urlApiProduct + "/" + id, Product.class);
    }

    public Product[] findProduct(Optional<String> searchValue, Optional<String> priceMinValue, Optional<String> priceMaxValue) {
        //  http://zuul:8092/product/find?priceMaxValue=1.1
        String request = urlApiProduct + "/find?searchValue=[SVAL]&priceMinValue=[PMIN]&priceMaxValue=[PMAX]";


        log.info("findProduct - Initial Request: "+request);
        if(searchValue.isPresent()) {
            String sv = searchValue.get();
            log.info("findProduct - [SVAL] is present");
            log.info("findProduct - Initial Request: " + request);
            if (sv.trim() != ""){// & sv!= null & sv != " " & sv.trim() != "") {
                log.info("findProduct - [SVAL] != \"\" - sv=["+sv+"]");
                request = request.replace("searchValue=[SVAL]", "searchValue=" + sv);
            }
            else{
                request = request.replace("searchValue=[SVAL]&", "");
            }
        }
        else{
            log.info("findProduct - [SVAL] is not present");
            request = request.replace("searchValue=[SVAL]&","");
        }

        log.info("findProduct - Modified Request1: "+request);

        if(priceMinValue.isPresent()){
            String pmin = priceMinValue.get();
            log.info("findProduct - [PMIN] is present");
            if(pmin.trim()!= ""){// & pmin!= null & pmin!= " " & pmin.trim() != ""){
                log.info("findProduct - [PMIN] != \"\" - pmin=["+pmin+"]");
                request = request.replace("priceMinValue=[PMIN]","priceMinValue="+pmin);
            }
            else{
                request = request.replace("priceMinValue=[PMIN]&","");
            }
        }
        else{
            log.info("findProduct - [PMIN] is not present");
            request = request.replace("priceMinValue=[PMIN]&","");
        }

        log.info("findProduct - Modified Request2: "+request);

        if(priceMaxValue.isPresent()) {
            String pmax = priceMaxValue.get();
            log.info("findProduct - [PMAX] is present");
            if (pmax.trim() != ""){// & pmax!= null & pmax!= " " & pmax.trim() != "") {
                log.info("findProduct - [PMAX] != \"\" - pmax=["+pmax+"]");
                request = request.replace("priceMaxValue=[PMAX]", "priceMaxValue=" + pmax);
            }
            else {
                request = request.replace("priceMaxValue=[PMAX]", "");
            }
        }
        else {
            log.info("findProduct - [PMAX] is not present");
            request = request.replace("priceMaxValue=[PMAX]", "");
        }

        log.info("findProduct - Modified Request3: "+request);


        //find?searchValue=[SVAL]&priceMinValue=[PMIN]&priceMaxValue=[PMAX]
        if(request.indexOf("searchValue=&") >= 0)
            request = request.replace("searchValue=&","");
        if(request.indexOf("priceMinValue=&") >= 0)
            request = request.replace("priceMinValue=&","");
        if(request.contains("priceMaxValue="))
            if(request.substring(request.indexOf("priceMaxValue=")).length() == "priceMaxValue=".length())
                request = request.replace("priceMaxValue=","");

        if(request.lastIndexOf("&") == request.length()-1)
            request = request.substring(0,request.lastIndexOf("&"));



        // Wenn keine Filter dann alle Produkte
        if(request.lastIndexOf("/find?") == request.length()-"/find?".length())
            request = urlApiProduct;

        log.info("findProduct - Modified Request - final: "+request);

        Product[] list = restTemplate.getForObject(request, Product[].class);
        return list;
    }

    public void addProduct(Product product) {
        restTemplate.postForLocation(urlApiProduct, product);
    }

}