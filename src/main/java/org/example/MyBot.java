//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example;

import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyBot extends TelegramLongPollingBot {
    public MyBot() {
    }

    public String getBotUsername() {
        return "@btde_karochi";
    }

    public String getBotToken() {
        return "7877821768:AAHkMKmzixa_X8GUylEsHqMgg1Qzk6cFVj0";
    }

    public void onUpdateReceived(Update update) {
        String chatId;
        if (update.hasMessage()) {
            chatId = update.getMessage().getChatId().toString();
            if (update.getMessage().hasText()) {
                switch (update.getMessage().getText()) {
                    case "/start" -> this.sendMainMenu(chatId);
                    case "\ud83d\udc51 Click Premium" -> this.sendTextMessageWithInlineKeyboard(chatId, "Click Premium xizmati haqida ma'lumot");
                    case "\ud83d\udcb3 Kartalarim" -> this.sendTextMessageWithInlineKeyboard(chatId, "Kartalarim haqida ma'lumot");
                    case "\ud83d\udcb8 To'lov" -> this.sendTextMessageWithInlineKeyboard(chatId, "To'lov qilindi");
                    case "\ud83d\udcb0 Balans" -> this.sendTextMessageWithInlineKeyboard(chatId, "Sizning balansingiz 100 000 so'm");
                    case "\ud83d\udd04 O'tkazmalar" -> this.sendTextMessageWithInlineKeyboard(chatId, "O'tkazmalar ro'yxati");
                    case "\ud83d\udcdc To'lovlar tarixi" -> this.sendTextMessageWithInlineKeyboard(chatId, "Sizning to'lovlar tarixingiz");
                    case "⚙ Kiruvchi hisoblar" -> this.sendTextMessageWithInlineKeyboard(chatId, "Kiruvchi hisoblar haqida ma'lumot");
                    case "⭐ Saralangan to'lovlar" -> this.sendTextMessageWithInlineKeyboard(chatId, "Saralangan to'lovlar ro'yxati");
                    default -> this.sendTextMessageWithInlineKeyboard(chatId, "Iltimos, tugmani bosing yoki haqiqiy buyruq kiriting.");
                }
            }

            if (update.getMessage().hasContact()) {
                chatId = update.getMessage().getContact().getPhoneNumber();
                String firstName = update.getMessage().getContact().getFirstName();
                this.sendTextMessage(chatId, "Sizning jo'natgan contact ma'lumotingiz:\nTelefon: " + chatId + "\nIsm: " + firstName);
            }

            if (update.getMessage().hasLocation()) {
                Double latitude = update.getMessage().getLocation().getLatitude();
                Double longitude = update.getMessage().getLocation().getLongitude();
                this.sendTextMessage(chatId, "Sizning jo'natgan lokatsiyangiz:\nKenglik: " + latitude + "\nUzunlik: " + longitude);
            }
        }

        if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getData();
            chatId = update.getCallbackQuery().getMessage().getChatId().toString();
            if ("chegirma_button".equals(chatId)) {
                this.sendTextMessage(chatId, "35000 so'mlik chegirma! Tebriklaymiz!");
            }
        }

    }

    private void sendMainMenu(String chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Iltimos, quyidagi variantlardan birini tanlang:");
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList();
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("\ud83d\udc51 Click Premium"));
        row1.add(new KeyboardButton("\ud83d\udcb3 Kartalarim"));
        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton("\ud83d\udcb8 To'lov"));
        row2.add(new KeyboardButton("\ud83d\udcb0 Balans"));
        KeyboardRow row3 = new KeyboardRow();
        row3.add(new KeyboardButton("\ud83d\udd04 O'tkazmalar"));
        row3.add(new KeyboardButton("\ud83d\udcdc To'lovlar tarixi"));
        KeyboardRow row4 = new KeyboardRow();
        row4.add(new KeyboardButton("⚙ Kiruvchi hisoblar"));
        row4.add(new KeyboardButton("⭐ Saralangan to'lovlar"));
        KeyboardRow row5 = new KeyboardRow();
        KeyboardButton contactButton = new KeyboardButton("\ud83d\udcf1 Contact jo'natish");
        contactButton.setRequestContact(true);
        KeyboardButton locationButton = new KeyboardButton("\ud83d\udccd Lokatsiya jo'natish");
        locationButton.setRequestLocation(true);
        row5.add(contactButton);
        row5.add(locationButton);
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);
        keyboard.add(row5);
        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        try {
            this.execute(message);
        } catch (TelegramApiException var13) {
            TelegramApiException e = var13;
            e.printStackTrace();
        }

    }

    private void sendTextMessageWithInlineKeyboard(String chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineButtons = new ArrayList();
        List<InlineKeyboardButton> row = new ArrayList();
        InlineKeyboardButton chegirmaButton = new InlineKeyboardButton();
        chegirmaButton.setText("35000 so'mlik chegirmaga");
        chegirmaButton.setCallbackData("chegirma_button");
        row.add(chegirmaButton);
        inlineButtons.add(row);
        inlineKeyboardMarkup.setKeyboard(inlineButtons);
        message.setReplyMarkup(inlineKeyboardMarkup);

        try {
            this.execute(message);
        } catch (TelegramApiException var9) {
            TelegramApiException e = var9;
            e.printStackTrace();
        }

    }

    private void sendTextMessage(String chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        try {
            this.execute(message);
        } catch (TelegramApiException var5) {
            TelegramApiException e = var5;
            e.printStackTrace();
        }

    }
}
