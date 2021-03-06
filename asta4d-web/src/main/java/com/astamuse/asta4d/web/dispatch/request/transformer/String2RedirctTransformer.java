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

package com.astamuse.asta4d.web.dispatch.request.transformer;

import com.astamuse.asta4d.web.dispatch.request.ResultTransformer;
import com.astamuse.asta4d.web.dispatch.response.provider.RedirectTargetProvider;
import com.astamuse.asta4d.web.util.DeclareInstanceUtil;

public class String2RedirctTransformer implements ResultTransformer {

    @Override
    public Object transformToContentProvider(Object result) {
        if (result instanceof String) {
            String target = result.toString();
            if (target.startsWith("redirect:")) {
                String path = target.substring("redirect:".length());
                RedirectTargetProvider provider = DeclareInstanceUtil.createInstance(RedirectTargetProvider.class);
                provider.setTargetPath(path);
                return provider;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
