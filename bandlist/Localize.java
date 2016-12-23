package com.company.bandlist;

import java.util.ArrayList;
import java.util.Locale;
import java.util.HashMap;
import java.util.PropertyResourceBundle;

/**
 * Created by dim on 23.12.16.
 */
public class Localize
{
    private static Localize instance;

    private HashMap<String, String> fields = new HashMap<String, String>();

    private Localize()
    {
        PropertyResourceBundle pr = (PropertyResourceBundle)PropertyResourceBundle.getBundle("com.company.bandlist.bandlist");
        fields.put("main.button.update", pr.getString("main.button.update"));
        fields.put("main.button.add", pr.getString("main.button.add"));
        fields.put("main.button.edit", pr.getString("main.button.edit"));
        fields.put("main.button.delete", pr.getString("main.button.delete"));
        fields.put("main.field.name", pr.getString("main.field.name"));
        fields.put("main.field.star", pr.getString("main.field.star"));
        fields.put("main.field.count", pr.getString("main.field.count"));
        fields.put("dialog.button.save", pr.getString("dialog.button.save"));
        fields.put("dialog.button.cancel", pr.getString("dialog.button.cancel"));
    }

    private Localize(String locale)
    {
        PropertyResourceBundle pr = (PropertyResourceBundle)PropertyResourceBundle.getBundle("com.company.bandlist.bandlist", new Locale(locale));
        fields.put("main.button.update", pr.getString("main.button.update"));
        fields.put("main.button.add", pr.getString("main.button.add"));
        fields.put("main.button.edit", pr.getString("main.button.edit"));
        fields.put("main.button.delete", pr.getString("main.button.delete"));
        fields.put("main.field.name", pr.getString("main.field.name"));
        fields.put("main.field.star", pr.getString("main.field.star"));
        fields.put("main.field.count", pr.getString("main.field.count"));
    }

    public static Localize getInstance()
    {
        if(instance == null){
            instance = new Localize();
        }
        return instance;
    }

    public static Localize getInstance(String locale)
    {
        if(instance == null){
            instance = new Localize(locale);
        }
        return instance;
    }

    public String getField(String key)
    {
        return fields.get(key);
    }
}
