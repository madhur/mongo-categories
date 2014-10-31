package com.akosha.inboundcms.domain.model.article;

import java.util.Date;
import java.util.List;

import com.akosha.inboundcms.domain.model.category.Category;

public class Article
{
	
	private String Id;
	
	private String title;
	
	private String body;
	
	private Date published;
	
	private Date updated;
	
	private boolean isPublished;
	
	private boolean isDeleted;
	
	private List<Category> categories;

	private Date getUpdated()
	{
		return updated;
	}

	private void setUpdated(Date updated)
	{
		this.updated = updated;
	}

	private Date getPublished()
	{
		return published;
	}

	private void setPublished(Date published)
	{
		this.published = published;
	}

	private boolean isPublished()
	{
		return isPublished;
	}

	private void setPublished(boolean isPublished)
	{
		this.isPublished = isPublished;
	}

	private boolean isDeleted()
	{
		return isDeleted;
	}

	private void setDeleted(boolean isDeleted)
	{
		this.isDeleted = isDeleted;
	}

	private String getBody()
	{
		return body;
	}

	private void setBody(String body)
	{
		this.body = body;
	}

	private String getTitle()
	{
		return title;
	}

	private void setTitle(String title)
	{
		this.title = title;
	}

	private String getId()
	{
		return Id;
	}

	private void setId(String id)
	{
		Id = id;
	}

}
 