package com.berzenin.university.web.controller;

import org.springframework.ui.Model;

public interface GenericViewController<E> {

	String getAll(Model model);

}