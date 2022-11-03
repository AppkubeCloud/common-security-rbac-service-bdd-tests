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

import com.synectiks.search.service.entities.Student;
import com.synectiks.security.entities.Permission;
import com.synectiks.security.testbase.TestBase;
import com.synectiks.security.utils.TestUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Title;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TMTests extends TestBase {

	public static int id = (int) Math.random();
	public static String name = "Test Student_" + TestUtils.getRandomString();
	public static float fee = 8494.3f;

	@Title("This test add new document in student index name")
	@Test
	public void test001() {
		Student st = new Student();
		st.setId(id);
		st.setName(name);
		st.setFee(fee);
		SerenityRest.rest().given().when().contentType(ContentType.JSON).header(new Header("index_name", "student"))
				.log().all().body(st).post("/saveDocs").then().log().all().statusCode(200);
	}

}
