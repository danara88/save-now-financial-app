package com.savenow.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.savenow.view.uiUtils.UiHelpers;
import com.savenow.model.common.BaseEntity;
import com.savenow.model.enums.TransactionType;

/**
 * Represents the Transaction domain entity
 */
@AllArgsConstructor
@ToString
@Getter
public class Transaction extends BaseEntity {
	/**
	 * Unique identifier
	 */
	private final String id;

	/**
	 * Box ID relation
	 */
	private final String boxId;

	/**
	 * Represents the box name impacted by the transaction
	 */
	private final String boxName;

	/**
	 * Represents the transaction reason details
	 */
	@Setter
	private String reason;

	/**
	 * Represents the total amount in the transaction
	 */
	private final double amount;

	/**
	 * Represents the transaction type
	 */
	@Setter
	private TransactionType type;

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
	public static Transaction createTransaction(String boxId, String boxName, String reason, double amount, TransactionType type) {
		return new Transaction(UUID.randomUUID()
			.toString(), boxId, boxName, reason, amount, type, UiHelpers.fromLocalDateTimeToFormattedDateTime(LocalDateTime.now()),
			null);
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
