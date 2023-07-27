package com.atmtrans.busbot.telegram.views;

import java.io.IOException;
import java.time.LocalDate;

import com.atmtrans.busbot.conversation.machine.states.PurchaseTicketsState;
import com.atmtrans.busbot.springframework.stereotype.StateNameBasedTelegramView;
import com.pengrad.telegrambot.model.request.LabeledPrice;
import com.pengrad.telegrambot.request.SendInvoice;


@StateNameBasedTelegramView(PurchaseTicketsState.WAIT_INVOICE)
public class Invoice implements View {

    private LocalDate month;

    @Override
    public void setModel(Object model) {
        month = (LocalDate) model;
    }

    @Override
    public SendInvoice render(Object chatId) throws IOException {
        var invoice = new SendInvoice(
            Long.valueOf(chatId.toString()),
            "Give me a bucks!",
            "a lot of bucks",
            "PAYLOAD_QWEAQWERFQWEF--",
            " token",
            "UAH",
            new LabeledPrice("item1", 600), new LabeledPrice("ticket2", 500)
        );
        invoice

            .providerData("""
                {"description": "general description",
                "split_rules" :
                                [
                                  {
                                    "public_key": "sandbox_", 
                                    "amount": 10,
                                    "commission_payer": "receiver",
                                    "description" : "first split"
                                  },
                                  {
                                    "public_key": "sandbox_",
                                    "amount": 1,
                                    "commission_payer": "receiver",
                                    "description" : "commision split"
                                  }
                                ]
                    }
                    """)
            .allowSendingWithoutReply(true)
            .photoUrl(
                "https://cdn.discordapp.com/attachments/995431514080813086/1108098196024070184/Artem_oldschool_bus_ticket_image_e8597bd7-c2ce-4561-a2eb-05e71ec3a2bd.png");

        return invoice;
    }
}
