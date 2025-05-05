package com.example.springai.demo.springai_demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springai.demo.springai_demo.application.writers.CustomDocumentWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/writers")
public class WritersController {

	@Autowired
	private CustomDocumentWriter documentWriter;

	@PostMapping("/file")
	public void writeDocuments() {
		documentWriter.writeDocuments(DocumentUtils.getDocuments());
	}
	
}
