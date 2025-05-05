package com.example.springai.demo.springai_demo.controllers;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springai.demo.springai_demo.application.readers.CustomJsonReader;
import com.example.springai.demo.springai_demo.application.readers.CustomMarkdownReader;
import com.example.springai.demo.springai_demo.application.readers.CustomTextReader;
import com.example.springai.demo.springai_demo.application.readers.CustomTikaReader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/readers")
public class ReadersController {

	@Autowired
	private CustomJsonReader jsonReader;
	
	@Autowired
	private CustomTextReader textReader;
	
	@Autowired
	private CustomMarkdownReader markdownReader;
	
	@Autowired
	private CustomTikaReader tikaReader;
	
	@GetMapping("/json")
	public List<Document> getDocumentsFromJsonReader() {
		return jsonReader.loadJson();
	}
	
	@GetMapping("/text")
	public List<Document> getDocumentsFromTextReader() {
		return textReader.loadText();
	}
	
	@GetMapping("/markdown")
	public List<Document> getDocumentsFromMarkdownReader() {
		return markdownReader.loadMarkdown();
	}
	
	@GetMapping("/tika")
	public List<Document> getDocumentsFromTikaReader() {
		return tikaReader.loadData();
	}
	
}
