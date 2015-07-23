package org.ligson.coderstar2.system.service.impl;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.system.service.FullTextSearchService;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by ligson on 2015/7/23.
 */
public class FullTextSearchServiceImpl implements FullTextSearchService {
    private IndexWriter articleWriter;
    private IndexReader articleReader;
    private IndexWriter questionWriter;
    private IndexReader questionReader;
    private File indexDir;
    private Analyzer analyzer;
    private Directory articleDirectory;
    private Directory questionDirectory;
    private static Logger logger = Logger.getLogger(FullTextSearchServiceImpl.class);

    public void init() {
        try {
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
            indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

            //文章
            File articleIndex = new File(indexDir,"article");
            if(!articleIndex.exists()){
                articleIndex.mkdirs();
            }
            articleDirectory = FSDirectory.open(articleIndex.toPath());
            articleWriter = new IndexWriter(articleDirectory,indexWriterConfig);
            articleReader = DirectoryReader.open(articleWriter,true);

            //问题
            File questionIndex = new File(indexDir,"question");
            if(!questionIndex.exists()){
                questionIndex.mkdirs();
            }
            questionDirectory = FSDirectory.open(articleIndex.toPath());
            questionWriter = new IndexWriter(questionDirectory,indexWriterConfig);
            questionReader = DirectoryReader.open(questionWriter,true);


        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void indexArticle(Article article) {

    }

    @Override
    public void indexQuestion(Question question) {

    }

    @Override
    public void removeArticle(Article article) {

    }

    @Override
    public void removeQuestion(Question question) {

    }

    @Override
    public void updateArticle(Article article) {

    }

    @Override
    public void updateQuestion(Question question) {

    }

    @Override
    public List<Question> relatedQuestion(Question question, int max) {
        return null;
    }

    @Override
    public List<Article> relatedArticle(Article question, int max) {
        return null;
    }

    @Override
    public List<String> hotQuestionKey(String key, int max) {
        return null;
    }

    @Override
    public List<String> hotArticleKey(String key, int max) {
        return null;
    }
}
