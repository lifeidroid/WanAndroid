package com.lifeidroid.wanandroid.api

import com.lifeidroid.wanandroid.http.CommonBean
import com.lifeidroid.wanandroid.model.entity.NavigationItem
import com.lifeidroid.wanandroid.model.entity.net.*
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface HttpApi {
    /**
     * 1.1 首页文章列表
     * @param page Int
     * @return CommonBean<ArticleEntity>
     */
    @GET("article/list/{page}/json")
    suspend fun getArticles(@Path("page") page: Int): CommonBean<ArticleEntity>

    /**
     * 11. 问答
     * @param page Int
     * @return CommonBean<QuestionEntity>
     */
    @GET("wenda/list/{page}/json ")
    suspend fun getWenDa(@Path("page") page: Int): CommonBean<QuestionEntity>

    /**
     * 1.5 置顶文章
     * @return CommonBean<ArticleEntity>
     */
    @GET("article/top/json")
    suspend fun getTopArticles(): CommonBean<MutableList<ArticleEntity.Data>>

    /**
     * 首页banner
     * @return CommonBean<BannerEntity>
     */
    @GET("banner/json")
    suspend fun getBanner(): CommonBean<MutableList<BannerEntity>>

    /**
     * 2.1 体系数据
     * @return CommonBean<SystemEntity>
     */
    @GET("tree/json")
    suspend fun getSystemTree(): CommonBean<MutableList<SystemEntity>>

    /**
     * 2.2 知识体系下的文章
     * @param page Int
     * @return CommonBean<ArticleEntity>
     */
    @GET("article/list/{page}/json")
    suspend fun getSystemArticleList(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): CommonBean<ArticleEntity>


    /**
     * 3.1 导航数据
     * @return CommonBean<SystemEntity>
     */
    @GET("navi/json")
    suspend fun getNaviTree(): CommonBean<MutableList<NavigationEntity>>

    /**
     * 5.1 登录
     * @return CommonBean<SystemEntity>
     */
    @POST("user/login")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): CommonBean<LoginEntity>

    /**
     * 12. 个人信息接口
     * @return CommonBean<UserInfoEntity>
     */
    @GET("user/lg/userinfo/json")
    suspend fun getUserInfo(): CommonBean<UserInfoEntity>

    /**
     * 6.2 收藏站内文章
     * @return CommonBean<String>
     */
    @POST("lg/collect/{id}/json")
    suspend fun collectArticle(
        @Path("id") id: Int
    ): CommonBean<String>

    /**
     * 6.3 收藏站外文章
     * @return CommonBean<String>
     */
    @POST("lg/collect/add/json")
    suspend fun collectArticle(
        @Query("title") title: String,
        @Query("author") author: String,
        @Query("link") link: String,
    ): CommonBean<CollecteEntity>

    /**
     * 6.4 取消收藏
     * 6.4.1 文章列表
     * @param id Int
     * @return CommonBean<String>
     */
    @POST("lg/uncollect_originId/{id}/json")
    suspend fun unCollectArticle(
        @Path("id") id: Int
    ): CommonBean<String>

    /**
     * 6.4 取消收藏
     * 6.4.2 我的收藏页面（该页面包含自己录入的内容）
     * @param id Int
     * @return CommonBean<String>
     */
    @POST("lg/uncollect/{id}/json")
    suspend fun unCollectMyArticle(
        @Path("id") id: Int,
        @Query("originId") originId: Int = -1
    ): CommonBean<String>

    /**
     * 10.5 分享文章
     * @return CommonBean<String>
     */
    @POST("lg/user_article/add/json")
    suspend fun shareArticle(
        @Query("title") title: String,
        @Query("link") link: String
    ): CommonBean<String>

    /**
     * 获取个人积分，需要登录后访问
     * @return CommonBean<String>
     */
    @GET("lg/coin/userinfo/json")
    suspend fun getMyCoinDetail(
    ): CommonBean<MyCoinDetailEntity>

    /**
     * 2.2 知识体系下的文章
     * @param page Int
     * @return CommonBean<ArticleEntity>
     */
    @GET("lg/coin/list/{page}/json")
    suspend fun getMyCoinHistory(
        @Path("page") page: Int
    ): CommonBean<MyCoinHistoryEntity>

    /**
     * 1.4 搜索热词
     * @return CommonBean<MutableList<HotKeyEntity>>
     */
    @GET("hotkey/json")
    suspend fun getHotKey(
    ): CommonBean<MutableList<HotKeyEntity>>

    @POST("article/query/{page}/json")
    suspend fun doSearch(
        @Path("page") page: Int,
        @Query("k") key: String
    ): CommonBean<ArticleEntity>
}