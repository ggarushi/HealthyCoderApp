package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DietPlannerTest {
	private DietPlanner dietPlanner;
	@BeforeEach
	void setup() {
		this.dietPlanner=new DietPlanner(20,30,50);
	}
	@Test
	void should_ReturnCorrect_DietPlan() {
		Coder coder=new Coder(1.82,75.0,26,Gender.MALE);
		DietPlan expected=new DietPlan(2202,110,73,275);
		DietPlan actual =dietPlanner.calculateDiet(coder);
		assertAll(
				()->assertEquals(expected.getCalories(),actual.getCalories()),
				()->assertEquals(expected.getCarbohydrate(),actual.getCarbohydrate()),
				()->assertEquals(expected.getFat(),actual.getFat()),
				()->assertEquals(expected.getProtein(),actual.getProtein())
				);
	}

}
