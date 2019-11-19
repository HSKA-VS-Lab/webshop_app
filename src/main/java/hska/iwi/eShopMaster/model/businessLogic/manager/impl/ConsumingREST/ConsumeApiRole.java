package hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST;


import org.springframework.web.client.RestTemplate;

public class ConsumeApiRole {
    
    private String urlApiRole = "http://localhost:8088/role";
    
    RestTemplate restTemplate = new RestTemplate();

    public Role[] getAllRoles() {
        return restTemplate.getForObject(urlApiRole, Role[].class);
    }

    public Role getRole(String input) {
        return restTemplate.getForObject(urlApiRole + "/" + input, Role.class);
    }

    public void addRole(String typ, int level) {
        restTemplate.postForLocation(urlApiRole, typ, level);
    }

    public void updateRole(int id, String typ, int level) {
        restTemplate.put(urlApiRole + "/" + id, typ, level);
    }

    public void deleteRole(int id){
        restTemplate.delete(urlApiRole + "/" + id);
    }

    public void deleteAllRoles(){
        restTemplate.delete(urlApiRole);
    }

}