package com.umasuraj.payroll.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author umasuraj
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiResponse {

	private String status;
	private String message;
}
