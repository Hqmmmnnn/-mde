package com.hqqm.mde.repositories;

import com.hqqm.mde.jooq.tables.Files;
import com.hqqm.mde.models.FileEntity;
import lombok.AllArgsConstructor;
import org.jooq.BatchBindStep;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class FileRepository {
    DSLContext context;

    private final Files f = Files.FILES;

    public void saveFiles(List<FileEntity> fileEntities) {
        BatchBindStep batch = context
                .batch(context.insertInto(f, f.ENGINE_ID, f.NAME, f.LOCATION, f.CONTENT_TYPE)
                              .values(       (Long) null, null  , null      , null          ));

        fileEntities.forEach(fileEntity ->
                batch.bind(fileEntity.getEngineId(), fileEntity.getName(), fileEntity.getLocation(), fileEntity.getContentType())
        );

        batch.execute();
    }
}
