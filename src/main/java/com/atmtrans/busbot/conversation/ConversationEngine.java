package com.atmtrans.busbot.conversation;

import com.atmtrans.busbot.conversation.machine.mv.ModelAndView;
import com.atmtrans.busbot.conversation.messages.AbstractConversationMessage;


public interface ConversationEngine {

    ModelAndView sendMessage(AbstractConversationMessage msg) throws Exception;
}
