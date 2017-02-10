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

import com.katsuraf.demoarchitecture.db.bean.ListItemEntity;
import com.katsuraf.demoarchitecture.db.bean.PageStateEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * An interface representing a user Cache.
 */
public interface ListCache {
    /**
     * Gets an {@link Observable} which will emit a {@link List <ListItemEntity>}.
     *
     * @param listType The user id to retrieve data.
     */
    Observable<List<ListItemEntity>> getListData(int listType);

    /**
     * Store list data.
     *
     * @param listItems items list
     */
    void putListData(List<ListItemEntity> listItems, int listType);

    /**
     * store page state
     *
     * @param listType  The list type that want to be clean
     * @param timestamp timestamp value
     * @param page      page state value
     */
    void putPageState(int listType, Integer page, Long timestamp);

    /**
     * Query page state about the listType
     *
     * @param listType The list type that want to be clean
     * @return page state value
     */
    PageStateEntity getPageState(int listType);

    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    boolean isExpired(int listType);

    /**
     * whether has the cache data with the listType
     *
     * @param listType The list type that want to be clean
     * @return has cache or not
     */
    boolean hasCache(int listType);

    /**
     * Evict all elements of the cache .
     *
     * @param listType The list type that want to be clean
     */
    void clearList(int listType);
}
