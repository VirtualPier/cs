package org.ligson.coderstar.search;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ligson on 2015/7/26.
 */
public class SpellTest {

    public static void main(String args[]) {
        String title = "spring获取依赖类的集合";
        SmartChineseAnalyzer smartChineseAnalyzer = new SmartChineseAnalyzer();
        try {
            TokenStream tokenStream = smartChineseAnalyzer.tokenStream("field", title);
            CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                System.out.println(charTermAttribute.toString());
            }
            tokenStream.end();
            tokenStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
