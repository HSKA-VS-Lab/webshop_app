package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.ConsumeApiCategory;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.ConsumeApiProduct;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductManagerImpl implements ProductManager {
	
	ConsumeApiProduct apiProduct = new ConsumeApiProduct();

	public List<Product> getProducts() {
		Product[] products = apiProduct.getProducts();
		List<Product> list = new ArrayList<Product>(products.length);
		for (Product product : products) {
			list.add(product);
		}
		return list;
	}
	
	public List<Product> getProductsForSearchValues(String searchDescription,
			Double searchMinPrice, Double searchMaxPrice) {	
		if (searchMinPrice == null) {
			searchMinPrice = Double.valueOf(0);
		}
		if (searchMaxPrice == null) {
			searchMaxPrice = Double.valueOf(9999999);
		}
		Product[] products = apiProduct.findProduct(searchDescription, searchMinPrice.toString(), searchMaxPrice.toString());
		if (products == null) {
			return new ArrayList<Product>();
		}
		List<Product> list = new ArrayList<Product>(products.length);
		for (Product product : products) {
			list.add(product);
		}
		return list;
	}

	public Product getProductById(int id) {
		return apiProduct.getProduct(id);
	}

	public Product getProductByName(String name) {
		Product[] products = apiProduct.findProduct(name, "0", "9999999");
		if (products != null && products.length >= 1) {
			return products[0];
		}
		return null;
	}
	
	public int addProduct(String name, double price, int categoryId, String details) {
		apiProduct.addProduct(name, price, categoryId, details);
		return 1; // TODO
	}
	

	public void deleteProductById(int id) {
		apiProduct.deleteProduct(id);
	}

	public boolean deleteProductsByCategoryId(int categoryId) {
		ConsumeApiCategory apiCategory = new ConsumeApiCategory();
		apiCategory.deleteCategory(categoryId);
		return true;
	}

}
