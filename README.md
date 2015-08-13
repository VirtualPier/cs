# cs
  数据库配置文件cs-config.properties
  tomcat配置时context path请设置成/

### api接口整理
/api/listCategory获取分类列表<br/>
/api/listArticleByCategory根据分类加载文章<br/>
/api/listQuestionByCategory根据分类加载问题<br/>
/api/viewArticle查看文章<br/>
/api/viewQuestion查看问题<br/>
/api/listArticleRemark加载文章评论<br/>
/api/listQuestionRemark加载问题评论<br/>

具体请参考:[https://github.com/ligson/cs/blob/master/src/main/java/org/ligson/coderstar2/system/controllers/ApiController.java]
