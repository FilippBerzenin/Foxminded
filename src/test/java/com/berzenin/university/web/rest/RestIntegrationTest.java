package com.berzenin.university.web.rest;

import org.springframework.beans.factory.annotation.Autowired;

import com.berzenin.university.web.IntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestIntegrationTest extends IntegrationTest {

	@Autowired
	ObjectMapper mapper;
}
