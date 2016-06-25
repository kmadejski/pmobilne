package pl.edu.pl.shopping.data;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.edu.pl.shopping.data.entity.ListItem;
import pl.edu.pl.shopping.data.entity.ShoppingList;

/**
 * Created by wojtek on 25.06.16.
 */

public class SmsParser {

    ShoppingRepository repository;
    Pattern pattern;
    Pattern itemsPattern;
    Pattern itemPattern;


    public SmsParser() {
        pattern = Pattern.compile("^Zakupy:(.*)?");
        itemsPattern = Pattern.compile("[a-zA-z]*-\\d*;");
        itemPattern = Pattern.compile("(.*)-(\\d*)");
        repository = new ShoppingRepository();
    }

    public void parser(String msg, String phone) {
        Matcher matcher = pattern.matcher(msg);
        if (matcher.matches()) {

            String str = matcher.group(1);
            ShoppingList list = new ShoppingList();
            list.setName("Lista od numeru: " + phone);
            list = repository.createShoppingList(list);


            Matcher itemsMatcher = itemsPattern.matcher(str);

            while (itemsMatcher.find()) {
                Matcher itemMatcher = itemPattern.matcher(itemsMatcher.group());
                ListItem item = new ListItem();
                itemMatcher.find();
                item.setShoppingID(list.getId());
                item.setName(itemMatcher.group(1));
                item.setQuantity(itemMatcher.group(2));

                repository.createListItem(item);

            }


        }
    }

}
