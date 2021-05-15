package com.hqqm.mde.repositories.impl;

import com.hqqm.mde.jooq.tables.*;
import com.hqqm.mde.models.CheckboxDTO;
import com.hqqm.mde.models.RangeDTO;
import com.hqqm.mde.repositories.EngineDataForFiltrationRepository;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.jooq.impl.DSL.max;
import static org.jooq.impl.DSL.min;

@Repository
@AllArgsConstructor
public class JooqEngineDataForFiltration implements EngineDataForFiltrationRepository {

    private final DSLContext context;
    private final Engines e = Engines.ENGINES_;
    private final CylinderQuantity cq = CylinderQuantity.CYLINDER_QUANTITY;
    private final RotationFrequency rf = RotationFrequency.ROTATION_FREQUENCY;
    private final ImoEcoStandard imo = ImoEcoStandard.IMO_ECO_STANDARD;
    private final EpaEcoStandard epa = EpaEcoStandard.EPA_ECO_STANDARD;
    private final EuEcoStandard eu = EuEcoStandard.EU_ECO_STANDARD;
    private final UicEcoStandard uic = UicEcoStandard.UIC_ECO_STANDARD;
    private final Manufacturers m = Manufacturers.MANUFACTURERS;
    private final Flanges f = Flanges.FLANGES;

    @Override
    public List<CheckboxDTO> getFlangeTypes() {
        return context.select(f.FLANGE_ID.as("id"), f.TYPE.as("name"), f.NOTE)
                .from(f)
                .orderBy(f.NOTE.asc())
                .fetch()
                .into(CheckboxDTO.class);
    }

    @Override
    public List<CheckboxDTO> getCylinderQuantity() {
        return context
                .selectDistinct(cq.QUANTITY.as("name"))
                .from(cq)
                .orderBy(cq.QUANTITY.asc())
                .fetch()
                .into(CheckboxDTO.class);
    }

    @Override
    public List<CheckboxDTO> getManufacturers() {
        return context
                .select(m.NAME.as("name"))
                .from(m)
                .orderBy(m.NAME.asc())
                .fetch()
                .into(CheckboxDTO.class);
    }

    @Override
    public List<CheckboxDTO> getRotationFrequencies() {
        return context
                .selectDistinct(rf.FREQUENCY.as("name"))
                .from(rf)
                .orderBy(rf.FREQUENCY.asc())
                .fetch()
                .into(CheckboxDTO.class);
    }

    @Override
    public List<CheckboxDTO> getImoEcoStandards() {
        return context
                .select(imo.QUOTE_NAME.as("name"))
                .from(imo)
                .fetch()
                .into(CheckboxDTO.class);
    }

    @Override
    public List<CheckboxDTO> getEpaEcoStandards() {
        return context
                .select(epa.QUOTE_NAME.as("name"))
                .from(epa)
                .fetch()
                .into(CheckboxDTO.class);
    }

    @Override
    public List<CheckboxDTO> getEuEcoStandards() {
        return context
                .select(eu.QUOTE_NAME.as("name"))
                .from(eu)
                .fetch()
                .into(CheckboxDTO.class);
    }

    @Override
    public List<CheckboxDTO> getUicEcoStandards() {
        return context
                .select(uic.QUOTE_NAME.as("name"))
                .from(uic)
                .fetch()
                .into(CheckboxDTO.class);
    }

    @Override
    public RangeDTO getMinAndMaxPowerRating() {
        return context
                .select(min(e.POWER_RATING).as("from"), max(e.POWER_RATING).as("to"))
                .from(e)
                .fetchOne()
                .into(RangeDTO.class);
    }

    @Override
    public RangeDTO getMinAndMaxWeightDryNoImplements() {
        return context
                .select(min(e.WEIGHT_DRY_NO_IMPLEMENTS).as("from"), max(e.WEIGHT_DRY_NO_IMPLEMENTS).as("to"))
                .from(e)
                .fetchOne()
                .into(RangeDTO.class);
    }

    @Override
    public RangeDTO getMinAndMaxLength() {
        return context
                .select(min(e.LENGTH).as("from"), max(e.LENGTH).as("to"))
                .from(e)
                .fetchOne()
                .into(RangeDTO.class);
    }

    @Override
    public RangeDTO getMinAndMaxWidth() {
        return context
                .select(min(e.WIDTH).as("from"), max(e.WIDTH).as("to"))
                .from(e)
                .fetchOne()
                .into(RangeDTO.class);
    }

    @Override
    public RangeDTO getMinAndMaxHeight() {
        return context
                .select(min(e.HEIGHT).as("from"), max(e.HEIGHT).as("to"))
                .from(e)
                .fetchOne()
                .into(RangeDTO.class);
    }
}
