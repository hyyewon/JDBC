package com.exception;

//사용자 정의 예외 클래스 => 예외발생시 try~catch 또는 throws에 사용할 수 있다.
public class DuplicatedDeptnoException extends Exception {

	//사용자 예외를 만드는 템플릿
	//필요시 변수, 메서드 추가 가능하지만 거의 x
	//1. String을 파라미터로 갖는 Exception 생성자 만들기
	public DuplicatedDeptnoException(String message) {
		super(message);
	}

	
	
}
