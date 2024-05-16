package uz.pdp.frondend.bot.maker;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.backend.model.users.Users;

public class MessageMaker {

    public SendMessage adminButton(Users curUser) {
        SendMessage sendMessage = new SendMessage(curUser.getId(), "Main Menu");
        InlineKeyboardButton[][] buttons = {
                {
                        new InlineKeyboardButton("Add book").callbackData("ADD_BOOK"),
                }
        };
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(buttons);
        sendMessage.replyMarkup(markup);
        return sendMessage;

    }

    public SendMessage mainButton(Users curUser) {
        SendMessage sendMessage = new SendMessage(curUser.getId(), "Main Menu");
        InlineKeyboardButton[][] buttons = {
                {
                        new InlineKeyboardButton("Download book").callbackData("DOWNLOAD_BOOK"),
                }
        };
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(buttons);
        sendMessage.replyMarkup(markup);
        return sendMessage;
    }

    public SendMessage chooseGenreButton(Users curUser) {
        SendMessage sendMessage = new SendMessage(curUser.getId(), "Select category:");
        InlineKeyboardButton[][] buttons = {
                {
                        new InlineKeyboardButton("Personal Development").callbackData("PERSONAL DEVELOPMENT"),
                        new InlineKeyboardButton("Thriller").callbackData("THRILLER"),
                },
                {
                        new InlineKeyboardButton("Romance").callbackData("ROMANCE"),
                        new InlineKeyboardButton("Fantasy").callbackData("FANTASY"),
                },
                {
                        new InlineKeyboardButton("Back").callbackData("BACK"),
                }
        };
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(buttons);
        sendMessage.replyMarkup(markup);
        return sendMessage;
    }
}
