package com.savenow.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.savenow.model.common.BaseEntity;

/**
 * Represents the Box domain entity
 */
@AllArgsConstructor
@ToString
@Getter
public class Box extends BaseEntity {

	/**
	 * Unique identifier
	 */
	private final String id;

	/**
	 * Name of the box
	 */
	@Setter
	private String name;

	/**
	 * Description of the box
	 */
	@Setter
	private String description;

	/**
	 * Indicates how much money is in the box
	 */
	public final double totalAmount;

	/**
	 * Entity creation date and time
	 */
	private final String createdAt;

	/**
	 * Entity update date and time
	 */
	@Setter
	private String updatedAt;

	/**
	 * Factory method for box creation.
	 * @param name represents name of the box.
	 * @param description represents descriotion of the box.
	 * @param initialAmount representints the initial amount money on the box.
	 * @return a Box domain entity.
	 */
	public static Box createBox(String name, String description, double initialAmount) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String creationDate = LocalDateTime.now().format(formatter);

		return new Box(UUID.randomUUID()
			.toString(), name, description, initialAmount, creationDate, null);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Box box = (Box) obj;
		return Objects.equals(name, box.getName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
