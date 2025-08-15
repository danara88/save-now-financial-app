package com.savenow.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.savenow.model.enums.TransactionType;

/**
 * Represents the Transaction domain entity
 */
@AllArgsConstructor
@ToString
@Getter
public class Transaction {
	/**
	 * Unique identifier
	 */
	private final String id;

	/**
	 * Box ID relation
	 */
	private final String boxId;

	/**
	 * Represents the transaction reason details
	 */
	private final String reason;

	/**
	 * Represents the transaction type
	 */
	private final TransactionType type;

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
	 * Factory method for transaction creation.
	 * @param boxId represents the box involved in the transaction.
	 * @param type represents the type of transaction.
	 * @return a Transaction domain entity.
	 */
	public static Transaction createTransaction(String boxId, String reason, TransactionType type) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String creationDate = LocalDateTime.now().format(formatter);
		return new Transaction(UUID.randomUUID().toString(), boxId, reason, type, creationDate, null);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Transaction transaction = (Transaction) obj;
		return Objects.equals(id, transaction.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
