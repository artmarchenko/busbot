package com.atmtrans.busbot.conversation.messages;


import com.atmtrans.busbot.conversation.machine.events.PurchaseTicketsEvent;
import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
public class Text extends AbstractConversationMessage {

    private static final PurchaseTicketsEvent EVENT = PurchaseTicketsEvent.TEXTED;

    private String text;

    public Text(long userId, String text) {
        super(userId);
        this.text = text;
    }

    @Override
    public PurchaseTicketsEvent getEvent() {
        return EVENT;
    }
}
