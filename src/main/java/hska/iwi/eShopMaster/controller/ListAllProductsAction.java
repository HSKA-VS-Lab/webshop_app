package hska.iwi.eShopMaster.controller;

import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.CategoryManagerImpl;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.Category;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.Product_Frontend;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ProductManagerImpl;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.Product;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.User;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ListAllProductsAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -94109228677381902L;
	
	User user;
	private List<Product> products;
	private List<Category> categories;
	private List<Product_Frontend> productsF;
	
	public String execute() throws Exception{
		String result = "input";
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		user = (User) session.get("webshop_user");
		
		if(user != null){
			System.out.println("list all products!");
			ProductManager productManager = new ProductManagerImpl();
			this.products = productManager.getProducts();
			result = "success";
		}

		CategoryManager categoryManager = new CategoryManagerImpl();
		categories = categoryManager.getCategories();


		for(Product p : products){
			for(Category c : categories) {
				if(c.getId() == p.getCategoryId()) {
					Product_Frontend pf = new Product_Frontend(p.getName(), p.getPrice(), c.getName(), p.getDetails());
					productsF.add(pf);
				}
			}
		}
		
		return result;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
