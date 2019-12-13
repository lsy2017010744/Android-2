package com.example.wordlist.model;

import java.io.Serializable;
import java.io.BufferedReader;import java.io.StringReader;
import java.util.ArrayList;

public class Words implements  Serializable, Comparable<Words> {
    public String word = null,//单词
            fy = null,//翻译
            sent = null,//例句
            sentTrans = null;//例句翻译

    public Words(String word,String fy,String sent,String sentTrans) {
        super();
        this.word = "" + word;
        this.fy = "" + fy;
        this.sent = "" + sent;
        this.sentTrans = "" + sentTrans;
    }
    public  Words(){
        super();
        this.word = "";//防止空指针
        this.fy = "";
        this.sent = "";
        this.sentTrans = "";
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getFy() {
        return fy;
    }

    public void setFy(String fy) {
        this.fy = fy;
    }

    public String getSent() {
        return sent;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }

    public String getSentTrans() {
        return sentTrans;
    }

    public void setSentTrans(String sentTrans) {
        this.sentTrans = sentTrans;
    }
    public  void printInfo(){
        System.out.println(this.word);
        System.out.println(this.fy);
        System.out.println(this.sent);
        System.out.println(this.sentTrans);

    }


    @Override
    public int compareTo(Words word) {
        if (this.getWord().compareTo(word.getWord()) > 0) {
            return 1;
        } else
            return -1;

    }
}
