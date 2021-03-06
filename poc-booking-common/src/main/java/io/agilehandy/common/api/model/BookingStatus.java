/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package io.agilehandy.common.api.model;

import java.util.Arrays;

/**
 * @author Haytham Mohamed
 **/

public enum BookingStatus {

	NEW ("NEW"),
	APPROVED("APPROVED"),
	CANCELLED("CANCELLED"),
	CHARGED("CHARGED"),
	IN_PROGRESS ("IN_PROGRESS"),
	COMPLETED("COMPLETED")
	;

	private String value;
	private static BookingStatus[] allValues = values();

	private BookingStatus(String val) {
		this.value = val;
	}

	public String getValue() {
		return this.value;
	}

	public static BookingStatus fromValue(String val) {
		return Arrays.asList(allValues)
				.stream()
				.filter(v -> v.getValue().equals(val))
				.findFirst()
				.orElseGet(() -> NEW);
	}
}
