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
package com.katsuraf.demoarchitecture.net;

import android.content.Context;

import com.katsuraf.demoarchitecture.R;


/**
 * Default subscriber base class to be used whenever you want default error handling.
 */
public abstract class DefaultSubscriber<T> extends AbsAPICallback<T> {
    protected DefaultSubscriber(String networkMsg, String parseMsg, String unknownMsg) {
        super(networkMsg, parseMsg, unknownMsg);
    }

    protected DefaultSubscriber(Context context) {
        super(context.getString(R.string.exp_msg_no_connection),
                context.getString(R.string.exp_msg_parse_failed),
                context.getString(R.string.exp_msg_unknown));
    }
}
