package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Nested;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
class BMICalculatorTest {
	@Nested
	class isDietRecommendedTests{
		@ParameterizedTest(name="weight{0},height{1}")
		@CsvSource(value={"100.0,1.72","95.0,1.75","111.0,1.78"})
		void should_ReturnTrue_when_DietRecommended(double coderWeight,double coderHeight) {
			double weight=coderWeight;
			double height=coderHeight;
			boolean recommended=BMICalculator.isDietRecommended(weight,height);
			assertTrue(recommended);
		}
		@Test
		void should_ReturnFalse_when_DietNotRecommended() {
			double weight=50.0;
			double height=1.92;
			boolean recommended=BMICalculator.isDietRecommended(weight,height);
			assertFalse(recommended);
		}
		@Test
		void should_ThrowArithmeticException_when_HeightZero() {
			double weight=50.0;
			double height=0.0;
			Executable executable=()->BMICalculator.isDietRecommended(weight,height);
			assertThrows(ArithmeticException.class,executable,"Height was zero");
		}

	}
	@Nested
	class coderWithWorstBMI{
		@Test
		void should_returnCoderWithWorstBMI() {
			List<Coder>coders=new ArrayList<>();
			coders.add(new Coder(1.82,98.0));
			coders.add(new Coder(1.82,64.7));
			coders.add(new Coder(1.80,60.0));
			Coder coderWorstBMI=BMICalculator.findCoderWithWorstBMI(coders);
			assertAll(
					()->assertEquals(1.82,coderWorstBMI.getHeight()),
					()->assertEquals(98.0,coderWorstBMI.getWeight())
					);
		}
		@Test
		void should_returnCoderWithWorstBMI_when_List_isEmpty() {
			List<Coder>coders=new ArrayList<>();
			Coder coderWorstBMI=BMICalculator.findCoderWithWorstBMI(coders);
			assertNull(coderWorstBMI,()->"The List of coders was empty");
		}
		@Test
		void should_returnCoderWithWorstBMI_withinTimeLimi() {
				List<Coder>coders=new ArrayList<>();
				Executable executable=()->BMICalculator.findCoderWithWorstBMI(coders);
				assertTimeout(Duration.ofMillis(2),executable);
		}
	}
	@Nested
		class CalculateBMIofAll{
			@Test
			void should_returnCorrectBMIscore() {
				List<Coder>coders=new ArrayList<>();
				coders.add(new Coder(1.82,98.0));
				coders.add(new Coder(1.82,64.7));
				coders.add(new Coder(1.80,60.0));
				double []expectedBMI= {29.59,19.53,18.52};
				double []actualBMIScores=BMICalculator.getBMIScores(coders);
				assertArrayEquals(expectedBMI,actualBMIScores);
			}
		}
	}
	
	
	
	


