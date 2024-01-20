package com.example.rateUniversityApplication.dao;

public class RequestModel<T> {
	public	String user;
	public	T  request;

    public RequestModel(String user, T courseName) {
        this.user = user;
        this.request = courseName;
    }
}
