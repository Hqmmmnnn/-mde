package com.hqqm.mde;

import com.hqqm.mde.jooq.enums.VesselTypes;
import com.hqqm.mde.jooq.tables.Engines;
import com.hqqm.mde.models.Engine;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class blabalaController {
    DSLContext jooq;

    @GetMapping("/getVesselTypes")
    public VesselTypes[] getVesselTypes() {
        return VesselTypes.values();
    }

    @GetMapping("/engines/1")
    public Engine getEngines() {
        Engines engines = Engines.ENGINES_;
        return jooq.selectFrom(engines)
                .where(engines.ENGINE_ID.eq(1))
                .fetchAny()
                .into(Engine.class);
    }
}