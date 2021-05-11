package com.hqqm.mde.repositories;

import com.hqqm.mde.jooq.tables.Files;
import com.hqqm.mde.models.FileEntity;
import lombok.AllArgsConstructor;
import org.jooq.BatchBindStep;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class JooqFileRepository implements FileRepository {
    private final DSLContext context;
    private final Files f = Files.FILES;

    public List<String> getFileNames(Long engineId) {
        return context
                .select(f.NAME)
                .from(f)
                .where(f.ENGINE_ID.eq(engineId))
                .fetch()
                .into(String.class);
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

    public void deleteEngineFiles(Long engineId) {
        context.delete(f)
                .where(f.ENGINE_ID.eq(engineId))
                .execute();
    }
}
