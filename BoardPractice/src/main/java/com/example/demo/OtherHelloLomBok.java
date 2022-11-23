package com.example.demo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class OtherHelloLomBok {
	
	private final String hello;
	private final int lombok;
	
	public static void main(String[] args) {
		OtherHelloLomBok hl = new OtherHelloLomBok("헬로",5);
		System.out.println(hl.getHello());
		System.out.println(hl.getLombok());
	}
}

/* Setter 을 사용하지 않고 속성에 final을 사용하여 @RequiredArgsConstructor 기능을 이용
 final의 기능은 값을 초기화한 이후는 값을 변경할 수 없다*/