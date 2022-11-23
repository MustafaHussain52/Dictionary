package com.example.dictionary;

import java.util.HashMap;
import java.util.Map;

public class DictionaryUsingHashMap {

    private HashMap<String,String> dictionary;
    public DictionaryUsingHashMap(){
        dictionary = new HashMap<String,String>();
        addListOfWords();
    }

    public boolean addWord(String word, String meaning){
        dictionary.put(word,meaning);
        return true;
    }

    public String[] getSuggestions(String word){
        String [] suggestions = new String[5];
            int i=0;
        for(Map.Entry<String,String> entry : dictionary.entrySet()){
            int lastIndex = Math.min(word.length(),entry.getKey().length());
            if(word.compareTo(entry.getKey().substring(0,lastIndex))==0){
                suggestions[i++] = entry.getKey();
            }
            if(i==4) break;
        }

        return suggestions;
    }

    public String findMeaning(String word){
        if(!dictionary.containsKey(word)){
            return "Word not found";
        }
        else return dictionary.get(word);
    }

    private String addListOfWords(){
        addWord("weeping","crying");
        addWord("minute","infinitely or immeasurably small");
        addWord("accord","concurrence of opinion");
        addWord("practice","a customary way of operation or behavior");
        addWord("intend","have in mind as a purpose");
        addWord("concern", "something that interests you because it is important");
        addWord("issue","some situation or event that is thought about");
        addWord("approach","move towards");
        addWord("establish","set up or found");
        addWord("utter","without qualification");
        addWord("obtain","come into possession of");
        addWord(" straight","successive, without a break");
        addWord("concept","an abstract or general idea inferred from specific instances");
        addWord("Mustafa","The chosen one");
        addWord("Rukaiya","Best of the Best,Rukaiya is Growth oriented, strong, visionary, adventurous,");
        addWord("Mohammed"," is an Arabic given male name literally meaning 'Praiseworthy'.");
        addWord("Husaina","Diminutive Of Husn; Beauty");

        return "";
    }

}
