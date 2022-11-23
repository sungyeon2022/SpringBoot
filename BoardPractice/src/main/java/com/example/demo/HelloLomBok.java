package com.example.demo;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HelloLomBok {
	private String hello;
	private int lombok;
	public static void main(String[] args) {
		HelloLomBok hl = new HelloLomBok();
		hl.setHello("헬로");
		hl.setLombok(5);
		
		System.out.println(hl.getHello());
		System.out.println(hl.getLombok());
	}
}
