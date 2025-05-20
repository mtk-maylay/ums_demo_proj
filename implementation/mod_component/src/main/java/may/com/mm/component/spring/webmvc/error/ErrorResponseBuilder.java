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

package may.com.mm.component.spring.webmvc.error;

import may.com.mm.component.exception.DomainException;
import may.com.mm.component.http.error.ErrorResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Component
public class ErrorResponseBuilder implements ErrorResponse.ErrorResponseBuilder {

    @Override
    public ErrorResponse build(Exception exception) {

        if (exception instanceof DomainException domainException) {

            return new ErrorResponse(domainException.getErrorMessage().code(), domainException.getErrorMessage().description());
        }

        if (exception instanceof MethodArgumentNotValidException mane) {

            return new ErrorResponse("RequestError",
                                     "Problem with request object. Possible cause(s): 1. Missing input(s). 2. Invalid data format.");
        }

        return new ErrorResponse("RequestError", exception.getMessage());
    }

}
