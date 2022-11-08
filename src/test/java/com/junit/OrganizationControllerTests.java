package com.synectiks.security.junit.ex;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.synectiks.security.entities.Permission;
import com.synectiks.security.testbase.TestBase;
import com.synectiks.security.utils.TestUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Title;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrganizationControllerTests extends TestBase{
	
	public static Long id;
	public static String name;
		
	@Title("This test case will test /organization/getAllOrganizations")
	@Test
	public void getAllOrganizations() {
		HashMap<String, Object> value= SerenityRest.rest().given()
		.when()
		.get("/organization/getAllOrganizations")
		.then()
		.log()
		.all()
		.statusCode(200)
		.extract()
		.path("findAll{it}.get(0)");
		System.out.println("value of : "+value);
		id=Long.valueOf(value.get("id").toString());
		name=value.get("name").toString();
	}
	
	@Title("This test case will test /organization/getOrganization/{id}")
	@Test
	public void getOrganizationById() {
		
		SerenityRest.rest().given()
		.when()
		.get("/organization/getOrganization/"+id)
		.then()
		.log()
		.all()
		.statusCode(200);
	}
	
	
	@Title("This test case will test /organization/getOrganizationByUserName?userName={}")
	@Test
	public void getOrganizationByUserName() {
		SerenityRest.rest().given()
		.when()
		.post("/permissions/delete/"+id)
		.then()
		.log()
		.all()
		.statusCode(200);
		
		SerenityRest.rest().given()
		.when()
		.get("/organization/getOrganizationByUserName?userName=admin.test@synectiks.com")
		.then()
		.log()
		.all()
		.statusCode(200);
	}
	
}
