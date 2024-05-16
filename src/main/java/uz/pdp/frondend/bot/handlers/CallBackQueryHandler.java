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
import java.util.Objects;

public class CallBackQueryHandler extends BaseHandler{

    @Override
    public void handle(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        String data = callbackQuery.data();
        User from = callbackQuery.from();
        super.curUser = getOrCreateUser(from);
        super.update = update;

        String baseStateStr = curUser.getBaseState();
        BaseState baseState = BaseState.valueOf(baseStateStr);
        if (Objects.equals(baseState, BaseState.MAIN_STATE)) {
          mainState();
        } else if (Objects.equals(baseState, BaseState.ADD_BOOK_STATE)) {

        } else if (Objects.equals(baseState, BaseState.SEARCH_BOOK_STATE)) {

        }

    }

    private void mainState() {
        String stateStr = curUser.getState();
        MainStates state = MainStates.valueOf(stateStr);
        CallbackQuery callbackQuery = update.callbackQuery();
        switch (state){
            case MAIN_MENYU -> {
                String data = callbackQuery.data();
                mainMenu(data);

            }
        }

    }

    private void mainMenu(String data) {
        switch (data){
            case "ADD_BOOK"->{
                enterBookName();
            }case "DOWNLOAD_BOOK"->{
                chooseGenre();
            }
        }
    }

    private void enterBookName() {
        Message message = update.message();
        User from = message.from();
        Long id = from.id();
        Integer idMessage = message.messageId();

    }

    private void chooseGenre() {
        SendMessage sendMessage=messageMaker.chooseGenreButton(curUser);
        bot.execute(sendMessage);
        curUser.setBaseState(BaseState.SEARCH_BOOK_STATE.name());
        curUser.setState(SearchBookState.SELECT_CATEGORY.name());
        userService.save(curUser);
    }
}
