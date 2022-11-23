package com.example.demo;

import lombok.Getter;

@Getter
public class NoSetter {
	private final String hello;
	private final int lom;
	public NoSetter(String hello, int lom) {
		this.hello = hello;
		this.lom = lom;
	}
	public static void main(String[] args) {
		NoSetter no = new NoSetter("Hello", 5);
		System.out.println(no.getHello());
		System.out.println(no.getLom());
	}
}
