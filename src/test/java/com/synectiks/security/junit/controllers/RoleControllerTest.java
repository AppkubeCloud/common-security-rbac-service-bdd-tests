package com.synectiks.security.junit.controllers;

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
import com.synectiks.security.entities.Role;
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
public class RoleControllerTest extends TestBase{
	
	public static long version=TestUtils.getRandomInt();
	public static String name="GAMA_USER_"+TestUtils.getRandomString();
	public static String permission="Account setting _"+TestUtils.getRandomString();
	public static String description="This role is for Account setting _"+TestUtils.getRandomString();
	public static Long id;

	@Title("This test will create new role")
	@Test
	public void test001() {
		Role role=new Role();
		role.setVersion(version);
		role.setName(name);
//		role.setPermission(permission);
		role.setDescription(description);
		SerenityRest.rest().given()
		.when()
		.contentType(ContentType.JSON)
		.log()
		.all()
		.body(role)
		.post("/roles/create")
		.then()
		.log()
		.all()
		.statusCode(201);
	}
	
	@Title("This is test case will check /roles/listAll api of security service")
	@Test
	public void test002() {
		String p1="findAll{it.name=='";
		String p2="'}.get(0)";
		HashMap<String, Object> value= SerenityRest.rest().given()
		.when()
		.get("/roles/listAll")
		.then()
		.log()
		.all()
		.statusCode(201)
		.extract()
		.path(p1+name+p2 );
//		System.out.println("Value of Hashmap :- "+value);
		assertThat(value,hasValue(name));
		id=Long.valueOf(value.get("id").toString());
	}
	
	
	@Title("This test will upadate the role")
	@Test
	public void test003() {
		name=name+"_Updated";
		Role role=new Role();
		role.setVersion(version);
		role.setName(name);
//		role.setPermission(permission);
		role.setDescription(description);
		SerenityRest.rest().given()
		.when()
		.contentType(ContentType.JSON)
		.log()
		.all()
		.body(role)
		.post("/roles/update")
		.then()
		.log()
		.all()
		.statusCode(200);
		String p1="findAll{it.name=='";
		String p2="'}.get(0)";
		HashMap<String, Object> value= SerenityRest.rest().given()
		.when()
		.get("/roles/listAll")
		.then()
		.log()
		.all()	
		.statusCode(201)
		.extract()
		.path(p1+name+p2 );
//		System.out.println("Value of Hashmap :- "+value);
		assertThat(value,hasValue(name));
	}
	
	@Title("This test will find role by id")
	@Test
	public void test004() {
		SerenityRest.rest().given()
		.when()
		.post("/roles/"+id)
		.then()
		.log()
		.all()
		.statusCode(201);
	}
	
	@Title("This test will delete permission by id")
	@Test
	public void test005() {
		SerenityRest.rest().given()
		.when()
		.post("/roles/delete/"+id)
		.then()
		.log()
		.all()
		.statusCode(200);
	}
}
