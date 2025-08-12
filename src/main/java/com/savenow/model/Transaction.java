package com.savenow.model;

import com.savenow.model.enums.TransactionType;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
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
	 * Represents the transaction type
	 */
	private final TransactionType type;

	/**
	 * Entity creation date and time
	 */
	private final LocalDateTime createdAt;
}
