/*-
 * ================================================================================
 * Vivify (sponsored by Jdev)
 * --------------------------------------------------------------------------------
 * Copyright (C) 2025 OpenVivify
 * --------------------------------------------------------------------------------
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ================================================================================
 */

package may.com.mm.component.spring.webmvc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import may.com.mm.component.utility.Snowflake;
import org.slf4j.MDC;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthenticationMDCInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) {

        MDC.remove("REQ_ID");
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        MDC.put("REQ_ID", String.valueOf(Snowflake.get().nextId()));

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {

            var principalId = (String) authentication.getPrincipal();
            MDC.put("USER_ID", principalId);
        }

        return true;
    }

}
