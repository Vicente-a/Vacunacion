package com.vacunas.controller;

public interface Bean {

	void init();
	void save();
	void delete();
	Boolean validate();
	void find();
	void findAll();
	void cancel();
}
