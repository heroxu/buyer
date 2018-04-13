package com.smyy.sharetour.buyer.db.operate;

import android.content.Context;

import com.smyy.sharetour.buyer.db.HomeSearch;
import com.smyy.sharetour.buyer.greendao.DaoMaster;
import com.smyy.sharetour.buyer.greendao.HomeSearchDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * create by xuxiarong on 2018/4/12
 */
public class HomeSearchDaoOpe {

    /**
     * 添加数据至数据库  
     *
     * @param context
     * @param hs
     */
    public static void insertData(Context context, HomeSearch hs) {
        DaoMaster.newDevSession(context,HomeSearchDao.TABLENAME).getHomeSearchDao().insert(hs);
    }


    /**
     * 将数据实体通过事务添加至数据库  
     *
     * @param context
     * @param list
     */
    public static void insertData(Context context, List<HomeSearch> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        DaoMaster.newDevSession(context,HomeSearchDao.TABLENAME).getHomeSearchDao().insertInTx(list);
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖  
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；  
     *
     * @param context
     * @param homeSearch
     */
    public static void saveData(Context context, HomeSearch homeSearch) {
        DaoMaster.newDevSession(context,HomeSearchDao.TABLENAME).getHomeSearchDao().save(homeSearch);
    }

    /**
     * 删除数据至数据库  
     *
     * @param context
     * @param homeSearch 删除具体内容
     */
    public static void deleteData(Context context, HomeSearch homeSearch) {
        DaoMaster.newDevSession(context,HomeSearchDao.TABLENAME).getHomeSearchDao().delete(homeSearch);
    }

    /**
     * 根据id删除数据至数据库  
     *
     * @param context
     * @param id      删除具体内容  
     */
    public static void deleteByKeyData(Context context, long id) {
        DaoMaster.newDevSession(context,HomeSearchDao.TABLENAME).getHomeSearchDao().deleteByKey(id);
    }

    /**
     * 删除全部数据  
     *
     * @param context
     */
    public static void deleteAllData(Context context) {
        DaoMaster.newDevSession(context,HomeSearchDao.TABLENAME).getHomeSearchDao().deleteAll();
    }

    /**
     * 更新数据库  
     *
     * @param context
     * @param homeSearch
     */
    public static void updateData(Context context, HomeSearch homeSearch) {
        DaoMaster.newDevSession(context,HomeSearchDao.TABLENAME).getHomeSearchDao().update(homeSearch);
    }


    /**
     * 查询所有数据  
     *
     * @param context
     * @return
     */
    public static List<HomeSearch> queryAll(Context context) {
        QueryBuilder<HomeSearch> builder = DaoMaster.newDevSession(context,HomeSearchDao.TABLENAME).getHomeSearchDao().queryBuilder();

        return builder.build().list();
    }



    /**
     *  分页加载  
     * @param context
     * @param pageSize 当前第几页(程序中动态修改pageSize的值即可)  
     * @param pageNum  每页显示多少个  
     * @return
     */
    public static List<HomeSearch> queryPaging( int pageSize, int pageNum,Context context){
        HomeSearchDao homeSearchDao = DaoMaster.newDevSession(context,HomeSearchDao.TABLENAME).getHomeSearchDao();
        List<HomeSearch> listMsg = homeSearchDao.queryBuilder()
                .offset(pageSize * pageNum).limit(pageNum).list();
        return listMsg;
    }

}
