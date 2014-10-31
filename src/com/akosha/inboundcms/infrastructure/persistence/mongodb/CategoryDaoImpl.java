package com.akosha.inboundcms.infrastructure.persistence.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.akosha.inboundcms.domain.model.category.Category;
import com.akosha.inboundcms.domain.model.category.CategoryDao;
import com.akosha.inboundcms.domain.model.company.Company;

public class CategoryDaoImpl implements CategoryDao
{
	private MongoOperations mongoOps;

	public CategoryDaoImpl(MongoOperations mongoOps)
	{
		this.mongoOps = mongoOps;
	}

	public List<Category> GetAllCategories(int companyId)
	{
		return null;

	}

	public void DeleteCategory(int companyId, String category)
	{
		// TODO Auto-generated method stub

	}
	
	public void AddCategory1(String parentCategory, String name, String companyId) throws Exception
	{
		
		if (parentCategory != null && parentCategory.length() != 0)
		{
			Query q = new Query();
			
			q.addCriteria(Criteria.where("name").is(parentCategory).and("companyId").is(companyId));
			
			Category parentObj = mongoOps.findOne(q, Category.class);
			if(parentObj!= null)
			{
				AddCategory(parentObj.getId().toString(), name, companyId);
			}
			else
				System.out.println("Parent category not found: " + parentCategory);
		}
		
	}

	public void AddCategory(String parentId, String name, String companyId)
			throws Exception
	{
		Category parentCategory = null;
		
		// Add company if it doesn't exist
		if (!CheckIfCompanyExist(companyId))
		{
			System.out.println("Adding company");
			AddCompany(companyId);
		}

		if (parentId != null && parentId.length() != 0)
		{
			if (!CheckIfCategoryExist(parentId))
				throw new Exception("ParentId does not exist");
			
			Query q = new Query();
			
			q.addCriteria(Criteria.where("_id").is(new ObjectId(parentId)).and("companyId").is(companyId));
			parentCategory = mongoOps.findOne(q, Category.class);

		}
		

		

		Category currentCategory = GetCategory(name, parentId, companyId);
		
		if (parentCategory != null)
		{
			System.out.println("Parent category is not null");
			
			List<Category> categories = parentCategory.getChildCategories();
			if (categories == null)
			{
				categories = new ArrayList<Category>();

			}
			
			System.out.println("Adding to parent category");
			categories.add(currentCategory);
			
			parentCategory.setChildCategories(categories);
			

			mongoOps.save(parentCategory);
		}
		else
		{
			System.out.println("Parent category is null, saving as top category");
			mongoOps.save(currentCategory);
			
		}

	}

	public void MoveCategory(String category, int companyId,
			String newParentCategory)
	{
		// TODO Auto-generated method stub

	}

	private Category GetCategory(String name, String parentId, String companyId)
	{
		Category cat = new Category();
		cat.setName(name);
		cat.setParentId(String.valueOf(parentId));
		cat.setCompanyId(companyId);

		return cat;
	}

	private boolean CheckIfCompanyExist(String Id)
	{
		Query q = new Query();
		q.addCriteria(Criteria.where("_id").is(Id));

		Company retValue = mongoOps.findOne(q, Company.class);

		if (retValue != null)
		{
			System.out.println(retValue.getId());
			System.out.println("company exists");
			return true;

		}

		System.out.println("company does not exist");
		return false;

	}

	private boolean CheckIfCategoryExist(String Id)
	{
		Query q = new Query();
		q.addCriteria(Criteria.where("_id").is(Id));

		Category retValue = mongoOps.findOne(q, Category.class);
		if (retValue != null)
		{
			System.out.println("category exists");
			return true;

		}

		System.out.println("category does not exists");
		return false;

	}

	public void AddCompany(String companyId)
	{
		Company company = new Company();
		company.setId(companyId);
		mongoOps.insert(company);
	}

}
