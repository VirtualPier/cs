# cs
  数据库配置文件cs-config.properties
  tomcat配置时context path请设置成/

### api接口整理
#### http://www.codertostar.com/api/listCategory获取分类列表<br/>
#### http://www.codertostar.com/api/listArticleByCategory根据分类加载文章<br/>
#### http://www.codertostar.com/api/listQuestionByCategory根据分类加载问题<br/>
#### http://www.codertostar.com/api/viewArticle查看文章<br/>
#### http://www.codertostar.com/api/viewQuestion查看问题<br/>
#### http://www.codertostar.com/api/listArticleRemark加载文章评论<br/>
#### http://www.codertostar.com/api/listQuestionRemark加载问题评论<br/>


具体请参考:[https://github.com/ligson/cs/blob/master/src/main/java/org/ligson/coderstar2/system/controllers/ApiController.java]<br/>
请求参数就是方法的参数。名字要一致<br/>
举例<br/>
http://www.codertostar.com/api/listQuestionByCategory<br/>
参数<br/>
categoryId<br/>
offset<br/>
max<br/>

请求方法post
返回的都是json格式


