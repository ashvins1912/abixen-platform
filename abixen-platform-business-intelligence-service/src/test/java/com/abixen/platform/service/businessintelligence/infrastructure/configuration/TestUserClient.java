/**
 * Copyright (c) 2010-present Abixen Systems. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.abixen.platform.service.businessintelligence.infrastructure.configuration;

import com.abixen.platform.common.application.dto.SimpleUserDto;
import com.abixen.platform.common.interfaces.client.UserClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class TestUserClient implements UserClient {
    @Override
    public SimpleUserDto getUserById(@PathVariable("id") Long id) {
        SimpleUserDto user = new SimpleUserDto();
        user.setId(1L);
        user.setUsername("joe.brown@abixen.com");
        return user;
    }
}