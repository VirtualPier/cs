package org.ligson.coderstar2.system.service.impl;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.spell.PlainTextDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.service.ArticleService;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.service.QuestionService;
import org.ligson.coderstar2.system.service.FullTextSearchService;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ligson on 2015/7/23.
 */
public class FullTextSearchServiceImpl implements FullTextSearchService {
    private IndexWriter articleWriter;
    private DirectoryReader articleReader;
    private IndexWriter questionWriter;
    private DirectoryReader questionReader;
    private File indexDir;
    private Analyzer analyzer;
    private Directory articleDirectory;
    private Directory questionDirectory;
    private static Logger logger = Logger.getLogger(FullTextSearchServiceImpl.class);
    private QuestionService questionService;
    private ArticleService articleService;
    private Directory spellcheckerDirectory;
    private File spellcheckDic;
    private SpellChecker spellChecker;


    public File getSpellcheckDic() {
        return spellcheckDic;
    }

    public void setSpellcheckDic(File spellcheckDic) {
        this.spellcheckDic = spellcheckDic;
    }

    public SpellChecker getSpellChecker() {
        return spellChecker;
    }

    public void setSpellChecker(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }

    public Directory getSpellcheckerDirectory() {
        return spellcheckerDirectory;
    }

    public void setSpellcheckerDirectory(Directory spellcheckerDirectory) {
        this.spellcheckerDirectory = spellcheckerDirectory;
    }

    public ArticleService getArticleService() {
        return articleService;
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    public IndexWriter getArticleWriter() {
        return articleWriter;
    }

    public void setArticleWriter(IndexWriter articleWriter) {
        this.articleWriter = articleWriter;
    }

    public IndexWriter getQuestionWriter() {
        return questionWriter;
    }

    public void setQuestionWriter(IndexWriter questionWriter) {
        this.questionWriter = questionWriter;
    }

    public File getIndexDir() {
        return indexDir;
    }

    public void setIndexDir(File indexDir) {
        this.indexDir = indexDir;
    }

    public Analyzer getAnalyzer() {
        return analyzer;
    }

    public void setAnalyzer(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

    public Directory getArticleDirectory() {
        return articleDirectory;
    }

    public void setArticleDirectory(Directory articleDirectory) {
        this.articleDirectory = articleDirectory;
    }

    public Directory getQuestionDirectory() {
        return questionDirectory;
    }

    public void setQuestionDirectory(Directory questionDirectory) {
        this.questionDirectory = questionDirectory;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        FullTextSearchServiceImpl.logger = logger;
    }

    public QuestionService getQuestionService() {
        return questionService;
    }

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public void init() {
        try {
            IndexWriterConfig articleIndexWriterConfig = new IndexWriterConfig(analyzer);
            articleIndexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

            IndexWriterConfig questionIndexWriterConfig = new IndexWriterConfig(analyzer);
            questionIndexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

            //文章
            File articleIndex = new File(indexDir, "article");
            if (!articleIndex.exists()) {
                articleIndex.mkdirs();
            }
            articleDirectory = FSDirectory.open(articleIndex.toPath());
            articleWriter = new IndexWriter(articleDirectory, articleIndexWriterConfig);
            articleReader = DirectoryReader.open(articleWriter, true);

            //问题
            File questionIndex = new File(indexDir, "question");
            if (!questionIndex.exists()) {
                questionIndex.mkdirs();
            }
            questionDirectory = FSDirectory.open(questionIndex.toPath());
            questionWriter = new IndexWriter(questionDirectory, questionIndexWriterConfig);
            questionReader = DirectoryReader.open(questionWriter, true);


            //拼写
            File spellCheckerIndex = new File(indexDir, "spellchecker");
            if (!spellCheckerIndex.exists()) {
                spellCheckerIndex.mkdirs();
            }
            spellcheckerDirectory = FSDirectory.open(spellCheckerIndex.toPath());
            spellChecker = new SpellChecker(spellcheckerDirectory);


        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }


    public void destory() {
        try {
            articleWriter.close();
            articleReader.close();
            questionWriter.close();
            questionReader.close();
        } catch (Exception e) {
            logger.error(e);
        }

    }

    private void addSpell(String title) {
        SmartChineseAnalyzer smartChineseAnalyzer = (SmartChineseAnalyzer) analyzer;
        try {
            TokenStream tokenStream = smartChineseAnalyzer.tokenStream("field", title);
            CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
            tokenStream.reset();
            FileWriter writer = new FileWriter(spellcheckDic, true);
            PrintWriter printWriter = new PrintWriter(writer);
            while (tokenStream.incrementToken()) {
                String word = charTermAttribute.toString();
                logger.debug(word);
                printWriter.println(word);
            }
            writer.close();
            printWriter.close();
            tokenStream.end();
            tokenStream.close();

            PlainTextDictionary plainTextDictionary = new PlainTextDictionary(new FileReader(spellcheckDic));
            IndexWriterConfig spellCheckerWriterConfig = new IndexWriterConfig(analyzer);
            spellChecker.indexDictionary(plainTextDictionary, spellCheckerWriterConfig, true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void indexArticle(Article article) {
        try {
            articleWriter.deleteDocuments(new Term("id", article.getId() + ""));
            articleWriter.commit();
        } catch (Exception e) {
            logger.error(e);
        }

        addSpell(article.getTitle());
        Field idField = new StringField("id", article.getId() + "", Field.Store.YES);
        Field titleField = new TextField("title", article.getTitle(), Field.Store.YES);
        Field contentField = new TextField("description", article.getDescription(), Field.Store.NO);
        Field createDateField = new StringField("createDate", article.getCreateDate(), Field.Store.YES);
        Field authorId = new LongField("authorId", article.getCreator().getId(), Field.Store.YES);
        Field authorName = new StringField("authorName", article.getCreator().getNickName(), Field.Store.YES);
        Field viewNumField = new LongField("viewNum", article.getViewNum(), Field.Store.YES);
        Field replyNumField = new LongField("replyNum", article.getReplyNum(), Field.Store.YES);
        Field attentionNumField = new LongField("attentionNum", article.getAttentionNum(), Field.Store.YES);

        Document document = new Document();
        document.add(idField);
        document.add(titleField);
        document.add(contentField);
        document.add(createDateField);
        document.add(authorId);
        document.add(authorName);
        document.add(viewNumField);
        document.add(replyNumField);
        document.add(attentionNumField);

        try {
            articleWriter.addDocument(document);
            articleWriter.commit();
        } catch (IOException e) {
            logger.error(e);
        }

    }

    @Override
    public void indexQuestion(Question question) {
        try {
            questionWriter.deleteDocuments(new Term("id", question.getId() + ""));
            questionWriter.commit();
        } catch (Exception e) {
            logger.error(e);
        }
        addSpell(question.getTitle());
        Field idField = new StringField("id", question.getId() + "", Field.Store.YES);
        Field titleField = new TextField("title", question.getTitle(), Field.Store.YES);
        Field contentField = new TextField("description", question.getDescription(), Field.Store.NO);
        Field createDateField = new StringField("createDate", question.getCreateDate(), Field.Store.YES);
        Field authorId = new LongField("authorId", question.getCreator().getId(), Field.Store.YES);
        Field authorName = new StringField("authorName", question.getCreator().getNickName(), Field.Store.YES);
        Field viewNumField = new LongField("viewNum", question.getViewNum(), Field.Store.YES);
        Field replyNumField = new LongField("replyNum", question.getReplyNum(), Field.Store.YES);
        Field attentionNumField = new LongField("attentionNum", question.getAttentionNum(), Field.Store.YES);

        Document document = new Document();
        document.add(idField);
        document.add(titleField);
        document.add(contentField);
        document.add(createDateField);
        document.add(authorId);
        document.add(authorName);
        document.add(viewNumField);
        document.add(replyNumField);
        document.add(attentionNumField);

        try {
            questionWriter.addDocument(document);
            questionWriter.commit();
        } catch (IOException e) {
            logger.error(e);
        }

    }

    @Override
    public void removeArticle(Article article) {
        try {
            articleWriter.deleteDocuments(new Term("id", article.getId() + ""));
        } catch (IOException e) {
            logger.error(e);
        }
    }

    @Override
    public void removeQuestion(Question question) {
        try {
            questionWriter.deleteDocuments(new Term("id", question.getId() + ""));
        } catch (IOException e) {
            logger.error(e);
        }
    }

    @Override
    public void updateArticle(Article article) {
        removeArticle(article);
        indexArticle(article);
    }

    @Override
    public void updateQuestion(Question question) {
        removeQuestion(question);
        indexQuestion(question);
    }

    @Override
    public List<Question> relatedQuestion(Question question, int max) {
        List<Question> questionList = new ArrayList<>();
        try {
            QueryParser queryParser = new QueryParser("title", analyzer);
            Query query = queryParser.parse(QueryParser.escape(question.getTitle()));
            IndexSearcher searcher = new IndexSearcher(getQuestionReader());
            TopDocs topDocs = searcher.search(query, max + 1);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for (int i = 0; i < scoreDocs.length; i++) {
                ScoreDoc scoreDoc = scoreDocs[i];
                Document document = searcher.doc(scoreDoc.doc);
                String idString = document.get("id");
                long id = Long.parseLong(idString);
                Question question1 = questionService.findQuestionById(id);
                if (question1 != null) {
                    questionList.add(question1);
                }
            }
            questionList.remove(question);
        } catch (Exception e) {
            logger.error(e);
        }
        return questionList;
    }

    public DirectoryReader getArticleReader() {
        try {
            if (articleReader == null) {
                articleReader = DirectoryReader.open(articleDirectory);
            } else {
                DirectoryReader tr = DirectoryReader.openIfChanged(articleReader);
                if (tr != null) {
                    articleReader.close();
                    articleReader = tr;
                }
            }
            return articleReader;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setArticleReader(DirectoryReader articleReader) {
        this.articleReader = articleReader;
    }

    public DirectoryReader getQuestionReader() {
        try {
            if (questionReader == null) {
                questionReader = DirectoryReader.open(questionDirectory);
            } else {
                DirectoryReader tr = DirectoryReader.openIfChanged(questionReader);
                if (tr != null) {
                    questionReader.close();
                    questionReader = tr;
                }
            }
            return questionReader;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setQuestionReader(DirectoryReader questionReader) {
        this.questionReader = questionReader;
    }

    @Override
    public List<Article> relatedArticle(Article article, int max) {
        List<Article> articleList = new ArrayList<>();
        try {
            QueryParser queryParser = new QueryParser("title", analyzer);
            Query query = queryParser.parse(QueryParser.escape(article.getTitle()));

            IndexSearcher searcher = new IndexSearcher(getArticleReader());
            TopDocs topDocs = searcher.search(query, max + 1);

            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for (int i = 0; i < scoreDocs.length; i++) {
                ScoreDoc scoreDoc = scoreDocs[i];
                Document document = searcher.doc(scoreDoc.doc);
                String idString = document.get("id");
                //System.out.println(idString);
                long id = Long.parseLong(idString);
                Article article1 = articleService.findArticleById(id);
                if (article1 != null) {
                    articleList.add(article1);
                }
            }
            articleList.remove(article);
        } catch (Exception e) {
            logger.error(e);
        }
        return articleList;
    }

    @Override
    public List<String> hotQuestionKey(String key, int max) {
        List<String> results = new ArrayList<>();
        try {
            spellChecker.setAccuracy(0.1f);
            String[] words = spellChecker.suggestSimilar(key, max);
            for (String word : words) {
                results.add(word);
            }
        } catch (IOException e) {
            logger.error(e);
        }
        return results;
    }

    @Override
    public List<String> hotArticleKey(String key, int max) {
        List<String> results = new ArrayList<>();
        try {
            spellChecker.setAccuracy(0.1f);
            String[] words = spellChecker.suggestSimilar(key, max);
            for (String word : words) {
                results.add(word);
            }
        } catch (IOException e) {
            logger.error(e);
        }
        return results;
    }

    private Document searchArticleById(long id) {
        Term term = new Term("id", id + "");
        TermQuery termQuery = new TermQuery(term);
        IndexSearcher searcher = new IndexSearcher(articleReader);
        List<Article> articleList = new ArrayList<>();
        try {
            TopDocs topDocs = searcher.search(termQuery, 1);
            if (topDocs.scoreDocs.length > 0) {
                return searcher.doc(topDocs.scoreDocs[0].doc);
            } else {
                return null;
            }
        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }


    private Document searchQuestionById(long id) {
        Term term = new Term("id", id + "");
        TermQuery termQuery = new TermQuery(term);
        IndexSearcher searcher = new IndexSearcher(questionReader);
        try {
            TopDocs topDocs = searcher.search(termQuery, 1);
            if (topDocs.scoreDocs.length > 0) {
                return searcher.doc(topDocs.scoreDocs[0].doc);
            } else {
                return null;
            }
        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }
}
