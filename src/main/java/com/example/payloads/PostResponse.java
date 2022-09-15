package com.example.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

	private List<PostDTO> content;
	
	private int pageNumber;
	
	private int pageSize;
	
	private long totalElements; // how many records
	
	private int totalPages; //total no. of pages
	
	private boolean lastPage; // is last page
}
