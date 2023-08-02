package com.atmtrans.busbot.conversation.messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public abstract class AbstractConversationMessage implements ConversationMessage {

    @Getter
    private final long userId;
}
