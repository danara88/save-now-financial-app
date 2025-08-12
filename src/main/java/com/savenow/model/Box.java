package com.savenow.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
@Getter
public class Box {
	/**
	 * Unique identifier
	 */
	private final String id;

	/**
	 * Name of the box
	 */
	private final String name;

	/**
	 * Description of the box
	 */
	private final String description;

	/**
	 * Indicates how much money is in the box
	 */
	public final double quantity;

	/**
	 * Entity creation date and time
	 */
	private final LocalDateTime createdAt;
}
