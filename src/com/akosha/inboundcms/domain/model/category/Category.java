package com.akosha.inboundcms.domain.model.category;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "category")
public class Category
{
	@Id
	private ObjectId Id;

	private String parentId;

	private String name;

	private Date updated;

	private boolean isDeleted;

	private boolean isPublished;

	private List<Category> children;

	private String companyId;

	public Category()
	{
		children = new ArrayList<Category>();
	}

	public String getParentId()
	{
		return parentId;
	}

	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Date getUpdated()
	{
		return updated;
	}

	public void setUpdated(Date updated)
	{
		this.updated = updated;
	}

	public boolean isDeleted()
	{
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted)
	{
		this.isDeleted = isDeleted;
	}

	public boolean isPublished()
	{
		return isPublished;
	}

	public void setPublished(boolean isPublished)
	{
		this.isPublished = isPublished;
	}

	public ObjectId getId()
	{
		return Id;
	}

	public void setId(ObjectId id)
	{
		Id = id;
	}

	public List<Category> getChildCategories()
	{
		return children;
	}

	public void setChildCategories(List<Category> childCategories)
	{
		this.children = childCategories;
	}

	public String getCompanyId()
	{
		return companyId;
	}

	public void setCompanyId(String companyId)
	{
		this.companyId = companyId;
	}

	public void print(String prefix, boolean isTail)
	{
		System.out.println(prefix + (isTail ? "└── " : "├── ") + name + "(" + companyId + ")");
		for (int i = 0; i < children.size() - 1; i++)
		{
			children.get(i).print(prefix + (isTail ? "    " : "│   "), false);
		}
		if (children.size() > 0)
		{
			children.get(children.size() - 1).print(
					prefix + (isTail ? "    " : "│   "), true);
		}
	}

}
