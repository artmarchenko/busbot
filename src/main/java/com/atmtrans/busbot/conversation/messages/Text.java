package com.atmtrans.busbot.conversation.messages;


import com.atmtrans.busbot.conversation.machine.events.RoutePurchaseEvent;
import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
public class Text extends AbstractConversationMessage {

    RoutePurchaseEvent event = RoutePurchaseEvent.TEXTED;

    String text;

    public Text(long userId, String text) {
        super(userId);
        this.text = text;
    }
}
