package com.atmtrans.busbot.conversation.messages;


import java.time.LocalDate;

import com.atmtrans.busbot.conversation.machine.events.PurchaseTicketsEvent;
import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
public class SelectVoyageDate extends AbstractConversationMessage {

    private static final @Getter PurchaseTicketsEvent event = PurchaseTicketsEvent.VOYAGE_DATE_SELECTED;

    private final LocalDate date;

    public SelectVoyageDate(long userId, LocalDate date) {
        super(userId);
        this.date = date;
    }
}
