package com.inacioalves.auth.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class fieldMessage implements Serializable {

	private static final long serialVersionUID = 1908238419106096853L;
	
	private String message;
	private String field;
}
