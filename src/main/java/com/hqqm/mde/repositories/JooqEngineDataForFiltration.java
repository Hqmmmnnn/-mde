package com.hqqm.mde.repositories;

import com.hqqm.mde.jooq.tables.*;
import com.hqqm.mde.models.CheckboxDTO;
import com.hqqm.mde.models.RangeDTO;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.max;
import static org.jooq.impl.DSL.min;

@Repository
@AllArgsConstructor
public class JooqEngineDataForFiltration implements EngineDataForFiltrationRepository {
    private final DSLContext context;

    private final Engines e = Engines.ENGINES_;
    private final Manufacturers m = Manufacturers.MANUFACTURERS;
    private final Flanges f = Flanges.FLANGES;

    @Override
    public List<CheckboxDTO> getFlangeTypes() {
        return context.select(f.FLANGE_ID.as("id"), f.TYPE.as("name"), f.NOTE)
                .from(f)
                .fetch()
                .into(CheckboxDTO.class);
    }

    @Override
    public List<CheckboxDTO> getCylinderQuantity() {
        return context
                .selectDistinct(e.CYLINDER_QUANTITY.as("name"))
                .from(e)
                .fetch()
                .into(CheckboxDTO.class);
    }

    @Override
    public List<CheckboxDTO> getManufacturers() {
        return context
                .select(m.NAME.as("name"))
                .from(m)
                .fetch()
                .into(CheckboxDTO.class);
    }

    @Override
    public List<CheckboxDTO> getRotationSpeed() {
        return context
                .selectDistinct(e.ROTATION_SPEED.as("name"))
                .from(e)
                .fetch()
                .into(CheckboxDTO.class);
    }

    @Override
    public List<CheckboxDTO> getImoEcoStandards() {
        var checkboxes = context
                .selectDistinct(e.IMO_ECO_STANDARD.as("name"))
                .from(e)
                .fetch()
                .into(CheckboxDTO.class);

        return checkboxes
                .stream()
                .filter(checkbox -> checkbox.getName() != null)
                .collect(Collectors.toList());
    }

    @Override
    public List<CheckboxDTO> getEpaEcoStandards() {
        var checkboxes = context
                .selectDistinct(e.EPA_ECO_STANDARD.as("name"))
                .from(e)
                .fetch()
                .into(CheckboxDTO.class);

        return checkboxes
                .stream()
                .filter(checkbox -> checkbox.getName() != null)
                .collect(Collectors.toList());
    }

    @Override
    public List<CheckboxDTO> getEuEcoStandards() {
        var checkboxes = context
                .selectDistinct(e.EU_ECO_STANDARD.as("name"))
                .from(e)
                .fetch()
                .into(CheckboxDTO.class);

        return checkboxes
                .stream()
                .filter(checkbox -> checkbox.getName() != null)
                .collect(Collectors.toList());
    }

    @Override
    public List<CheckboxDTO> getUicEcoStandards() {
        var checkboxes = context
                .selectDistinct(e.UIC_ECO_STANDARD.as("name"))
                .from(e)
                .fetch()
                .into(CheckboxDTO.class);

        return checkboxes
                .stream()
                .filter(checkbox -> checkbox.getName() != null)
                .collect(Collectors.toList());
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
