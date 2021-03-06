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

package com.astamuse.asta4d.misc.spring.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.astamuse.asta4d.Context;
import com.astamuse.asta4d.web.WebApplicationContext;

public class Asta4dTemplateContextInitializer extends HandlerInterceptorAdapter implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Context templateContext = Context.getCurrentThreadContext();
        if (templateContext == null) {
            templateContext = applicationContext.getBean(WebApplicationContext.class);
            Context.setCurrentThreadContext(templateContext);
        }
        templateContext.init();
        WebApplicationContext webContext = (WebApplicationContext) templateContext;
        webContext.setRequest(request);
        webContext.setResponse(response);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Context templateContext = Context.getCurrentThreadContext();
        if (templateContext != null) {
            templateContext.clear();
        }
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.applicationContext = context;
    }

}
