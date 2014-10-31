import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.akosha.inboundcms.domain.model.category.Category;
import com.akosha.inboundcms.domain.model.company.Company;
import com.akosha.inboundcms.infrastructure.persistence.mongodb.CategoryDaoImpl;

public class Main
{

	public static void main(String[] args)
	{
		ApplicationContext ctx = new GenericXmlApplicationContext(
				"SpringConfig.xml");
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		CategoryDaoImpl c = new CategoryDaoImpl(mongoOperation);
		try
		{

			int a;
			String childCategory, company, category, parentCategory;
			String s;
			InputStreamReader str= new InputStreamReader (System.in);
			Scanner i = new Scanner(System.in);
			BufferedReader in= new BufferedReader (str);
			
			while (true)
			{
				System.out.println("1 Add a company");
				System.out.println("2 Add a category");
				System.out.println("3 Add a child category");
				System.out.println("4 Print list of categories");
				System.out.println("5 Print list of companies");
				System.out.println("9 Exit");

				s = in.readLine();

				switch (Integer.parseInt(s))
				{
				case 1:
					System.out.println("Enter company id");
					s = in.readLine();
					c.AddCompany(s);
					
					break;

				case 2:
					System.out.println("Enter company id. If not exists, it will be added");
					s = in.readLine();
					System.out.println("Enter new category name");
					category = in.readLine();					
					c.AddCategory("", category, s);
					
					break;

				case 3:
					System.out.println("Enter company id");
					s = in.readLine();
					System.out.println("Enter parent category name");
					parentCategory=in.readLine();
					System.out.println("Enter new category name");
					childCategory = in.readLine();
					c.AddCategory1(parentCategory, childCategory,  s);
					break;

				case 4:

					List<Category> categorylist = mongoOperation
							.findAll(Category.class);

					for (Category categoryObj : categorylist)
						categoryObj.print("", false);

					break;
					
				case 5:

					List<Company> companylist = mongoOperation
							.findAll(Company.class);

					for (Company categoryObj : companylist)
						System.out.println(categoryObj.getId());

					break;
					
				case 9:
					System.exit(1);

				}

			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
