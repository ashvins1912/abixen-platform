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


package com.abixen.platform.service.businessintelligence.multivisualisation.application.service.parser.impl;

import com.abixen.platform.service.businessintelligence.multivisualisation.application.service.parser.FileDataParserService;
import com.abixen.platform.service.businessintelligence.multivisualisation.application.service.parser.FileParserFactory;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("fileParserFactoryImpl")
public class FileDataParserFactoryImpl implements FileParserFactory {

    private FileDataParserService csvFileDataParserService;
    private FileDataParserService excelFileDataParserService;

    @Autowired
    public FileDataParserFactoryImpl(@Qualifier("csvParserService") FileDataParserService csvFileDataParserService,
                                     @Qualifier("excelParserService") FileDataParserService excelFileDataParserService) {
        this.csvFileDataParserService = csvFileDataParserService;
        this.excelFileDataParserService = excelFileDataParserService;
    }

    @Override
    public FileDataParserService getParse(String extension) {
        switch (extension) {
            case ".csv":
                return csvFileDataParserService;
            case ".xls":
                return excelFileDataParserService;
            case ".xlsx":
                return excelFileDataParserService;
            default:
                throw new NotImplementedException("Parser for this file isn't implemented");
        }
    }
}
