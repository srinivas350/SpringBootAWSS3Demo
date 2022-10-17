package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.service.AwsS3Service;

@RestController
@RequestMapping("/aws")
public class AwsS3DemoController {
	
	@Autowired
	public AwsS3Service service;
	
	@PostMapping("/uploadFile")
	public ResponseEntity<String> uploadFile(@RequestParam(value="file") MultipartFile file)
	{
		return new ResponseEntity<String>(service.uploadFile(file),HttpStatus.OK);
	}
	
	@GetMapping("/downloadFile/{fileName}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("fileName")String fileName)
	{
		byte[] b=service.downloadFile(fileName);
		ByteArrayResource resource=new ByteArrayResource(b);
		return ResponseEntity.ok().contentLength(b.length).header("Content-type","application/octset-stream")
														  .header("Content-Disposition", "attachment; fileName=\""+fileName+"\"")
														  .body(resource);
	}
	
	@DeleteMapping("/delete/{fileName}")
	public ResponseEntity<String> deleteFile(@PathVariable ("fileName") String fileName)
	{
		return new ResponseEntity<String>(service.deleteFile(fileName),HttpStatus.OK);
	}

}
