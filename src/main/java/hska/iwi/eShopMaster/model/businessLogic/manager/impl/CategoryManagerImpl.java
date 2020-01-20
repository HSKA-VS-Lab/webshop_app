package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.Category;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.ConsumeApiCategory;

import java.util.ArrayList;
import java.util.List;

public class CategoryManagerImpl implements CategoryManager{

	ConsumeApiCategory apiCategory = new ConsumeApiCategory();

	public List<Category> getCategories() {
		Category[] categories = apiCategory.getCategories();
		List<Category> list = new ArrayList(categories.length);
		for (Category category : categories) {
			list.add(category);
		}
		return list;
	}

	public Category getCategory(int id) {
		return apiCategory.getCategory(id);
	}

	public Category getCategoryByName(String name) {
		List<Category> list = getCategories();
		for (Category cat : list) {
			if (cat.getName().equals(name)) {
				return cat;
			}
		}
		return null;
	}

	public void addCategory(String name) {
		apiCategory.addCategory(name);
	}

	public void delCategory(Category cat) {
// 		Products are also deleted because of relation in Category.java 
		apiCategory.deleteCategory(cat.getId());
	}

	public void delCategoryById(int id) {
		apiCategory.deleteCategory(id);
	}
}
