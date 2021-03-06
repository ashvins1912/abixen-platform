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

import com.abixen.platform.service.businessintelligence.multivisualisation.application.message.FileParserMessage;
import com.abixen.platform.service.businessintelligence.multivisualisation.application.service.parser.FileDataParserService;
import com.abixen.platform.service.businessintelligence.multivisualisation.application.service.parser.impl.preparer.FileDataPreparer;
import com.abixen.platform.service.businessintelligence.multivisualisation.application.service.parser.impl.reader.CsvReaderService;
import com.abixen.platform.service.businessintelligence.multivisualisation.application.service.parser.impl.validator.FileDataValidator;
import com.abixen.platform.service.businessintelligence.multivisualisation.domain.model.impl.file.DataFileColumn;
import com.abixen.platform.service.businessintelligence.multivisualisation.application.dto.datafile.DataFileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("csvParserService")
class CsvFileDataParserServiceImpl implements FileDataParserService {

    private final CsvReaderService reader;
    private final FileDataValidator validator;
    private final FileDataPreparer fileDataPreparer;

    @Autowired
    CsvFileDataParserServiceImpl(CsvReaderService reader,
                                 FileDataValidator validator,
                                 FileDataPreparer fileDataPreparer) {
        this.reader = reader;
        this.validator = validator;
        this.fileDataPreparer = fileDataPreparer;
    }

    @Override
    public FileParserMessage<DataFileColumn> parse(final MultipartFile multipartFile, final Boolean readFirstColumnAsColumnName) {
        final FileParserMessage<DataFileColumn> msg = new FileParserMessage<>();
        final DataFileDto readedData = reader.read(multipartFile, readFirstColumnAsColumnName, msg);
        if (validator.valid(readedData, msg, readFirstColumnAsColumnName)) {
            msg.setData(fileDataPreparer.prepareData(readedData, msg, readFirstColumnAsColumnName));
        }
        return msg;
    }
}
