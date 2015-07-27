package org.ligson.coderstar.search;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.ligson.coderstar2.article.domains.Article;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ligson on 2015/7/26.
 */
public class SpellTest {

    public static void spell() {
        String title = "你好吗，想说这句话？";
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

    public static void search() throws Exception {
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        FSDirectory articleDirectory = FSDirectory.open(new File("D:\\temp\\index\\index\\article").toPath());
        DirectoryReader articleReader = DirectoryReader.open(articleDirectory);


        IndexSearcher searcher = new IndexSearcher(articleReader);


        try {

            QueryParser queryParser = new QueryParser("title", analyzer);
            Query query = queryParser.parse("精通 Grails: 构建您的第一个 Grails 应用程序");


            //Term term = new Term("id", "32");
            //TermQuery query = new TermQuery(term);
            TopDocs topDocs = searcher.search(query, 10);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for (int i = 0; i < scoreDocs.length; i++) {
                ScoreDoc scoreDoc = scoreDocs[i];
                Document document = searcher.doc(scoreDoc.doc);
                String idString = document.get("id");
                System.out.println(document.get("title"));
                System.out.println(idString);
            }

        } catch (Exception e) {

        }

    }

    public static void main(String args[]) throws Exception {
       // search();
        spell();
    }
}
