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

import jakarta.validation.ValidationException;
import may.com.mm.component.exception.DomainException;
import may.com.mm.component.http.error.ErrorResponse;
import may.com.mm.component.exception.InputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

    private final ErrorResponse.ErrorResponseBuilder errorResponseBuilder;

    @Autowired
    public ErrorHandler(ErrorResponse.ErrorResponseBuilder errorResponseBuilder) {

        this.errorResponseBuilder = errorResponseBuilder;
    }

    @ExceptionHandler(value = {DomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handle(DomainException exception) {

        LOGGER.warn("DomainException - Received error : [{}]", exception.getMessage());
        LOGGER.error("Error:", exception);
        return this.errorResponseBuilder.build(exception);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handle(RuntimeException exception) {

        LOGGER.warn("RuntimeException - Received error : [{}]", exception.getMessage());
        LOGGER.error("Error:", exception);
        return this.errorResponseBuilder.build(exception);
    }

    @ExceptionHandler(value = {InputException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handle(InputException exception) {

        LOGGER.warn("InputException - Received error : [{}]", exception.getMessage());
        LOGGER.error("Error:", exception);
        return this.errorResponseBuilder.build(exception);
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handle(Exception exception) {

        LOGGER.warn("Exception - Received error : [{}]", exception.getMessage());
        LOGGER.error("Error:", exception);
        return this.errorResponseBuilder.build(exception);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class, ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handle(MethodArgumentNotValidException exception) {

        LOGGER.warn("MethodArgumentNotValidException - Received error : [{}]", exception.getMessage());
        LOGGER.error("Error:", exception);
        return this.errorResponseBuilder.build(exception);
    }

}
