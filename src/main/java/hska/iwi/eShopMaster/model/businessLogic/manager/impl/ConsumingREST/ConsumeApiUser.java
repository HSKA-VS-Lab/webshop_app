package hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST;

import org.springframework.web.client.RestTemplate;

public class ConsumeApiUser {

    private String urlApiUser = "http://zuul:8092/user";
    
    RestTemplate restTemplate = new RestTemplate();

    public User[] getAllUsers() {
        return restTemplate.getForObject(urlApiUser, User[].class);
    }

    public User getUser(String input) {
        return restTemplate.getForObject(urlApiUser + "/" + input, User.class);
    }

    public void addUser(User user) {
        restTemplate.postForLocation(urlApiUser, user);
    }

    public void addUser(String firstname, String lastname, String username, String password) {
        restTemplate.postForLocation(urlApiUser, firstname, lastname, username, password);
    }

    public void updateUser(int id, String firstname, String lastname, String username, String password) {
        restTemplate.put(urlApiUser + "/" + id, firstname, lastname, username, password);
    }

    public void deleteAllUsers() {
        restTemplate.delete(urlApiUser);
    }

    public void deleteUser(int id) {
        restTemplate.delete(urlApiUser + "/" + id);
    }



}