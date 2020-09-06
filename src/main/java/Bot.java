import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.BotOptions;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {

    private String name;
    private String token;
    private Map<Long,TelegramUser> userData;
    private File file = new File("C://Users//povar//IdeaProjects//BOTP","REST.rar");


    public Bot(String name, String token) {
        this.name = name;
        this.token = token;
        userData = new HashMap<>();
    }




    @SneakyThrows
    public void onUpdateReceived(Update update) {
        TelegramUser user = getOrCreate(update);
        SendMessage sendMessage = new SendMessage();
        SendDocument sendDocument = new SendDocument();
        Message message = update.getMessage();
      if(update.hasMessage()){
          if (message != null && message.hasText()){
              switch (message.getText()){
                  case "/start":
                      sendMsg(user.chatId,"hello friend");
                      break;
                  case "помощь":
                      sendMsg(user.chatId,"enter password");
                      break;
                  case "12345":
                      sendMsg(user.chatId,"correct password");
                      execute(new SendDocument().setDocument(file).setChatId(user.chatId));
                      break;

              }

          }
      }

    }

    public String getBotUsername() {
        return "York_photosbot";
    }

    public String getBotToken() {
        return "1184978943:AAEQxbjyypBkHmL1JFyU_fs6SKSgdeUtHoA";
    }


    public TelegramUser getOrCreate(Update update){
        long userId = 0;
        if (update.hasMessage()){
            userId = update.getMessage().getFrom().getId();

        }else if(update.hasCallbackQuery()){
            userId = update.getCallbackQuery().getFrom().getId();
        }
        TelegramUser result;
        if(userData.containsKey(userId)){
            result = userData.get(userId);
        }else{
            result = new TelegramUser();
            result.userId =userId;
            userData.put(userId,result);
        }
        if(update.hasMessage()){
            result.chatId = update.getMessage().getChatId();
        }
        return result;
    }

    public synchronized void sendMsg(long chatId, String s) {

        SendMessage sendMessage = new SendMessage();

        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}
