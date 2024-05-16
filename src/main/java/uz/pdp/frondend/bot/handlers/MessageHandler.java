package uz.pdp.frondend.bot.handlers;

import com.pengrad.telegrambot.model.Contact;import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.model.Contact;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.backend.model.book.Books;
import uz.pdp.backend.model.users.Users;
import uz.pdp.backend.service.BookService;
import uz.pdp.backend.service.UserService;
import uz.pdp.bean.BeanController;
import uz.pdp.MainBot;
import uz.pdp.frondend.bot.maker.MessageMaker;
import uz.pdp.frondend.bot.states.base.BaseState;
import uz.pdp.frondend.bot.states.child.MainStates;
import uz.pdp.frondend.bot.states.child.SearchBookState;

import java.util.Objects;

public class MessageHandler extends BaseHandler {

    @Override
    public void handle(Update update) {
        Message message = update.message();
        User from = message.from();
        super.update = update;
        super.curUser=getOrCreateUser(from);
        String text = message.text();
        super.
        if (Objects.equals(text, "/start")) {
            if (Objects.isNull(curUser.getPhoneNumber()) || curUser.getPhoneNumber().isEmpty()) {
                enterPhoneNumber();
            } else {
                mainMenyu();
            }
        } else {
            String baseStateStr = curUser.getBaseState();
            BaseState baseState = BaseState.valueOf(baseStateStr);
            if (Objects.equals(baseState, BaseState.MAIN_STATE)) {
                mainState();
            } else if (Objects.equals(baseState, BaseState.ADD_BOOK_STATE)) {
                addBookState();
            } else if (Objects.equals(baseState, BaseState.SEARCH_BOOK_STATE)) {
                searchBookState();
            }
        }
    }


    private void searchBookState() {
    }

    private void addBookState() {

    }

    private void mainState() {
        String stateStr = curUser.getState();
        MainStates state = MainStates.valueOf(stateStr);
        switch (state) {
            case REGISTER -> {
                Message message = update.message();
                Contact contact = message.contact();
                if (contact != null) {
                    String phoneNumber = contact.phoneNumber();
                    curUser.setPhoneNumber(phoneNumber);
                    userService.save(curUser);
                    mainMenyu();
                } else {
                    incorrectData("Phone Number");
                }
            }
            case MAIN_MENYU -> {

            }

        }
    }

    private void mainMenyu() {

        if (curUser.getId() == 197849661) {
            SendMessage sendMessage = messageMaker.adminButton(curUser);
            bot.execute(sendMessage);
            curUser.setState(MainStates.MAIN_MENYU.name());
            userService.save(curUser);
        } else {
            SendMessage sendMessage1 = messageMaker.mainButton(curUser);
            bot.execute(sendMessage1);
            curUser.setState(MainStates.MAIN_MENYU.name());
            userService.save(curUser);
        }


    }

    private void incorrectData(String data) {
        bot.execute(new SendMessage(curUser.getId(), "You entered incorrect " + data));

    }


    public void enterPhoneNumber() {
        SendMessage sendMessage = new SendMessage(curUser.getId(), "Enter phone number");
        KeyboardButton[][] buttons = {
                {new KeyboardButton("Phone number").requestContact(true)}
        };
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(buttons).oneTimeKeyboard(true);
        sendMessage.replyMarkup(replyKeyboardMarkup);

        bot.execute(sendMessage);

    }
}














       /* SendMessage message1 = new SendMessage(from.id(), "         Choose genre: ");

        InlineKeyboardButton[][] buttons = new InlineKeyboardButton[2][2];
        buttons[0][0] = new InlineKeyboardButton("Personal Development").callbackData("1");
        buttons[0][1] = new InlineKeyboardButton("Thriller").callbackData("2");
        buttons[1][0] = new InlineKeyboardButton("Romance").callbackData("3");
        buttons[1][1] = new InlineKeyboardButton("Fantasy").callbackData("4");

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(buttons);
        message1.replyMarkup(markup);

        bot.execute(message1);*/


