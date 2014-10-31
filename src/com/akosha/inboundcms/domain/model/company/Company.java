package com.akosha.inboundcms.domain.model.company;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="company")
public class Company
{
	@Id
	private String Id;
	
	public String getId()
	{
		return Id;
	}



	public void setId(String id)
	{
		Id = id;
	}



	
}
