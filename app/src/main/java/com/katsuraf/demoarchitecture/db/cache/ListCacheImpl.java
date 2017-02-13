/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.katsuraf.demoarchitecture.db.cache;

import android.content.Context;

import com.katsuraf.demoarchitecture.db.DBHelper;
import com.katsuraf.demoarchitecture.db.bean.ListItemEntity;
import com.katsuraf.demoarchitecture.db.bean.PageStateEntity;
import com.katsuraf.demoarchitecture.net.exception.ResultException;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


/**
 * {@link ListCache} implementation.
 */
public class ListCacheImpl implements ListCache {
    private static final long EXPIRATION_TIME = 60 * 1000;

    private final DBHelper dbHelper;

    /**
     * Constructor of the class {@link ListCacheImpl}.
     *
     * @param context  A
     */
    public ListCacheImpl(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Invalid null parameter");
        }
        this.dbHelper = new DBHelper(context);
    }

    @Override
    public Observable<List<ListItemEntity>> getListData(int listType) {
        return Observable.create(subscriber -> {
            List<ListItemEntity> listItemEntityList = dbHelper.queryListData(listType);
            if (listItemEntityList != null && listItemEntityList.size() > 0) {
                subscriber.onNext(listItemEntityList);
                subscriber.onComplete();
            } else {
                subscriber.onError(
                        new ResultException(ResultException.NO_DATA, ResultException.MSG_NO_DATA_FOUND));
            }
        });
    }


    @Override
    public void putListData(List<ListItemEntity> listItems, int listType) {
        this.executeAsynchronously(new CacheWriter(dbHelper, listItems));
    }

    @Override
    public void putPageState(int listType, Integer page, Long timestamp) {
        dbHelper.addPageState(listType, page, timestamp);
    }

    @Override
    public PageStateEntity getPageState(int listType) {
        return dbHelper.queryPageState(listType);
    }

    @Override
    public boolean isExpired(int listType) {
        PageStateEntity pageState = getPageState(listType);
        Long currentTime = System.currentTimeMillis();
        return (pageState == null
                || pageState.getTimestamp() == null
                || currentTime - pageState.getTimestamp() * 1000 > EXPIRATION_TIME);
    }

    @Override
    public boolean hasCache(int listType) {
        return dbHelper.hasListData(listType);
    }

    @Override
    public void clearList(int listType) {
        this.executeAsynchronously(new CacheCleaner(this.dbHelper, listType));
    }

    /**
     * Executes a {@link Runnable} in another Thread.
     *
     * @param runnable {@link Runnable} to execute
     */
    private void executeAsynchronously(Runnable runnable) {
        Observable.just(runnable).subscribeOn(Schedulers.io());
    }

    /**
     * {@link Runnable} class for writing to disk.
     */
    private static class CacheWriter implements Runnable {
        private final DBHelper dbHelper;
        private final List<ListItemEntity> listItems;

        CacheWriter(DBHelper dbHelper, List<ListItemEntity> listItems) {
            this.dbHelper = dbHelper;
            this.listItems = listItems;
        }

        @Override
        public void run() {
            this.dbHelper.insertListBeans(listItems);
        }
    }

    /**
     * {@link Runnable} class for evicting all the cached files
     */
    private static class CacheCleaner implements Runnable {
        private final DBHelper dbHelper;
        private final int listType;

        CacheCleaner(DBHelper dbHelper, int listType) {
            this.dbHelper = dbHelper;
            this.listType = listType;
        }

        @Override
        public void run() {
            this.dbHelper.deleteListData(this.listType);
        }
    }
}
