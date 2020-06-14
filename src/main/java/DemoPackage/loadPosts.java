package DemoPackage;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;

public class loadPosts {
	
	private HttpResponse<JsonNode> jsResponse;
	@BeforeClass
	public void loadPostbyId() throws UnirestException
	{
		jsResponse	= Unirest.get("https://my-json-server.typicode.com/typicode/demo/posts/1")
				.header("acept", "application/json").asJson();
				
	}
	@Test (priority =1)
	public void verifyStatus() 
	{
		Assert.assertEquals(200, jsResponse.getStatus(), "Typicode API- Get method has error");
	}

	@Test (priority =2)
	public void verifyResponseNotNull() 
	{
		Assert.assertNotNull(jsResponse.getBody(), "Typicode API- Post title should not be blank");
	}
	
	@Test (priority =3)
	public void verifyStructure() 
	{
		Assert.assertEquals(2, jsResponse.getBody().getObject().length(), "Typicode API- JSON must has two columns");

	}

	@Test (priority =4)
	public void verifyPostTitle() 
	{
		Assert.assertNotNull(jsResponse.getBody().getObject().getString("title"), "Typicode API- Post title should not be blank");
	}
	
}
