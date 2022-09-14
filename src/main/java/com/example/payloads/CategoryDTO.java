package com.example.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class CategoryDTO {

	private long id;
	@NotBlank
	@Size(min = 4)
	private String categoryTitle;

	@NotBlank
	@Size(min = 10)
	private String categoryDescription;

}
