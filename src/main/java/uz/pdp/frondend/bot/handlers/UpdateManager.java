package uz.pdp.frondend.bot.handlers;

import com.pengrad.telegrambot.TelegramBot;
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


public class UpdateManager  {
 public BaseHandler messageHandler;
 public BaseHandler callBackHandler;

    public UpdateManager() {
        this.messageHandler = new MessageHandler();
        this.callBackHandler = new CallBackQueryHandler();
    }

    public void manage (Update update){
        if(update.message()!=null) {
            messageHandler.handle(update);
        } else if (update.callbackQuery()!=null) {
            callBackHandler.handle(update);
        }
    }
}

