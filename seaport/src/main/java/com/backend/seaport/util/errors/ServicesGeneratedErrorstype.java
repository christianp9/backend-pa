/**
 * 
 */
package com.backend.seaport.util.errors;

import lombok.Data;

/**
 * @author christian
 *
 */
@Data
public class ServicesGeneratedErrorstype {

	private String errorMessage;
	
	public ServicesGeneratedErrorstype(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	
};
