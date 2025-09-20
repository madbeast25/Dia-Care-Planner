package com.varshaDTOs;

public class ChatReq {

	private int age;
	private String sex;
	private double weight;
	private int height;
	private double bmi;
	private double pre_meals_glucose;
	private double post_meals_glucose;
	private double cholestrol;
	
	
	
	public ChatReq(int age, String sex, double weight, int height, double bmi, double pre_meals_glucose,
			double post_meals_glucose, double cholestrol) {
		super();
		this.age = age;
		this.sex = sex;
		this.weight = weight;
		this.height = height;
		this.bmi = bmi;
		this.pre_meals_glucose = pre_meals_glucose;
		this.post_meals_glucose = post_meals_glucose;
		this.cholestrol = cholestrol;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public double getBmi() {
		return bmi;
	}
	public void setBmi(double bmi) {
		this.bmi = bmi;
	}
	public double getPre_meals_glucose() {
		return pre_meals_glucose;
	}
	public void setPre_meals_glucose(double pre_meals_glucose) {
		this.pre_meals_glucose = pre_meals_glucose;
	}
	public double getPost_meals_glucose() {
		return post_meals_glucose;
	}
	public void setPost_meals_glucose(double post_meals_glucose) {
		this.post_meals_glucose = post_meals_glucose;
	}
	public double getCholestrol() {
		return cholestrol;
	}
	public void setCholestrol(double cholestrol) {
		this.cholestrol = cholestrol;
	}
	
	
}
