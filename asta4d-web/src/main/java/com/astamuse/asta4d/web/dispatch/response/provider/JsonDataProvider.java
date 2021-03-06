/*
 * Copyright 2012 astamuse company,Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.astamuse.asta4d.web.dispatch.response.provider;

import com.astamuse.asta4d.web.dispatch.response.writer.ContentWriter;
import com.astamuse.asta4d.web.dispatch.response.writer.JsonWriter;

public class JsonDataProvider implements ContentProvider<Object> {

    private Object data;

    public JsonDataProvider() {
        this(null);
    }

    public JsonDataProvider(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public boolean isContinuable() {
        return false;
    }

    @Override
    public Object produce() throws Exception {
        return data;
    }

    @Override
    public Class<? extends ContentWriter<Object>> getContentWriter() {
        return JsonWriter.class;
    }

}
