package com.hqqm.mde.repositories.impl;

import com.hqqm.mde.jooq.tables.Files;
import com.hqqm.mde.jooq.tables.records.FilesRecord;
import com.hqqm.mde.models.Engine;
import com.hqqm.mde.models.EngineFileNames;
import com.hqqm.mde.models.FileEntity;
import com.hqqm.mde.repositories.FileRepository;
import lombok.AllArgsConstructor;
import org.jooq.BatchBindStep;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class JooqFileRepository implements FileRepository {
    private final DSLContext context;
    private final Files f = Files.FILES;

    public List<EngineFileNames> getFileNames(Long engineId, List<String> names) {
        return context
                .select(f.FILE_ID.as("id"), f.NAME)
                .from(f)
                .where(f.ENGINE_ID.eq(engineId).and(f.NAME.in(names)))
                .fetch()
                .into(EngineFileNames.class);
    }

    public List<EngineFileNames> getFileNames(Long engineId) {
        return context
                .select(f.FILE_ID.as("id"), f.NAME)
                .from(f)
                .where(f.ENGINE_ID.eq(engineId))
                .fetch()
                .into(EngineFileNames.class);
    }

    public void saveFiles(List<FileEntity> files) {
        BatchBindStep batch = context
                .batch(context.insertInto(f, f.ENGINE_ID, f.NAME, f.LOCATION, f.CONTENT_TYPE)
                              .values(       (Long) null, null  , null      , null          ));

        files.forEach(fileEntity ->
                batch.bind(
                        fileEntity.getEngineId(),
                        fileEntity.getName(),
                        fileEntity.getLocation(),
                        fileEntity.getContentType()));

        batch.execute();
    }

    public String deleteFile(Long fileId) {
        String path = context
                .select(f.LOCATION)
                .from(f)
                .where(f.FILE_ID.eq(fileId))
                .fetchOne()
                .getValue(f.LOCATION);

        context.delete(f)
                .where(f.FILE_ID.eq(fileId))
                .execute();

        return path;
    }
}
