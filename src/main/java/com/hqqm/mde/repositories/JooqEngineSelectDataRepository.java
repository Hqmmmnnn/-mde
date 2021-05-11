package com.hqqm.mde.repositories;

import com.hqqm.mde.jooq.tables.*;
import com.hqqm.mde.models.EngineSelectData;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class JooqEngineSelectDataRepository implements EngineSelectDataRepository {
    private final DSLContext context;

    @Override
    public List<EngineSelectData> getManufacturersData() {
        Manufacturers m = Manufacturers.MANUFACTURERS;

        return context
                .select(m.MANUFACTURER_ID.as("id"), m.NAME.as("value"))
                .from(m)
                .fetch()
                .into(EngineSelectData.class);
    }

    @Override
    public List<EngineSelectData> getRotationFrequenciesData() {
        RotationFrequency rf = RotationFrequency.ROTATION_FREQUENCY;

        return context
                .select(rf.ROTATION_FREQUENCY_ID.as("id"), rf.FREQUENCY.as("value"))
                .from(rf)
                .fetch()
                .into(EngineSelectData.class);
    }

    @Override
    public List<EngineSelectData> getCylindersQuantityData() {
        CylinderQuantity cq = CylinderQuantity.CYLINDER_QUANTITY;

        return context
                .select(cq.CYLINDER_QUANTITY_ID.as("id"), cq.QUANTITY.as("value"))
                .from(cq)
                .fetch()
                .into(EngineSelectData.class);
    }

    @Override
    public List<EngineSelectData> getCylinderArrangementsData() {
        CylinderArrangements ca = CylinderArrangements.CYLINDER_ARRANGEMENTS;

        return context
                .select(ca.CYLINDER_ARRANGEMENT_ID.as("id"), ca.ARRANGEMENT.as("value"))
                .from(ca)
                .fetch()
                .into(EngineSelectData.class);
    }

    @Override
    public List<EngineSelectData> getInjectionTypesData() {
        InjectionTypes it = InjectionTypes.INJECTION_TYPES;

        return context
                .select(it.INJECTION_TYPE_ID.as("id"), it.TYPE.as("value"))
                .from(it)
                .fetch()
                .into(EngineSelectData.class);
    }

    @Override
    public List<EngineSelectData> getVesselTypesData() {
        VesselTypes vt = VesselTypes.VESSEL_TYPES;

        return context
                .select(vt.VESSEL_TYPE_ID.as("id"), vt.TYPE.as("value"))
                .from(vt)
                .fetch()
                .into(EngineSelectData.class);
    }

    @Override
    public List<EngineSelectData> getCoolingSystemTypesData() {
        CoolingSystemTypes cst = CoolingSystemTypes.COOLING_SYSTEM_TYPES;

        return context
                .select(cst.COOLING_SYSTEM_TYPE_ID.as("id"), cst.TYPE.as("value"))
                .from(cst)
                .fetch()
                .into(EngineSelectData.class);
    }

    @Override
    public List<EngineSelectData> getImoEcoStandardsData() {
        ImoEcoStandard imo = ImoEcoStandard.IMO_ECO_STANDARD;

        return context
                .select(imo.IMO_ECO_STANDARD_ID.as("id"), imo.QUOTE_NAME.as("value"))
                .from(imo)
                .fetch()
                .into(EngineSelectData.class);
    }

    @Override
    public List<EngineSelectData> getEpaEcoStandardsData() {
        EpaEcoStandard epa = EpaEcoStandard.EPA_ECO_STANDARD;

        return context
                .select(epa.EPA_ECO_STANDARD_ID.as("id"), epa.QUOTE_NAME.as("value"))
                .from(epa)
                .fetch()
                .into(EngineSelectData.class);
    }

    @Override
    public List<EngineSelectData> getEuEcoStandardsData() {
        EuEcoStandard eu = EuEcoStandard.EU_ECO_STANDARD;

        return context
                .select(eu.EU_ECO_STANDARD_ID.as("id"), eu.QUOTE_NAME.as("value"))
                .from(eu)
                .fetch()
                .into(EngineSelectData.class);
    }

    @Override
    public List<EngineSelectData> getUicEcoStandardsData() {
        UicEcoStandard uic = UicEcoStandard.UIC_ECO_STANDARD;

        return context
                .select(uic.UIC_ECO_STANDARD_ID.as("id"), uic.QUOTE_NAME.as("value"))
                .from(uic)
                .fetch()
                .into(EngineSelectData.class);
    }

    @Override
    public List<EngineSelectData> getAssignmentsData() {
        Assignments a = Assignments.ASSIGNMENTS;

        return context
                .select(a.ASSIGNMENT_ID.as("id"), a.ASSIGNMENT.as("value"))
                .from(a)
                .fetch()
                .into(EngineSelectData.class);
    }

    @Override
    public List<EngineSelectData> getEngineRatingData() {
        Ratings r = Ratings.RATINGS;

        return context
                .select(r.RATING_ID.as("id"), r.LOAD_MODE.as("value"))
                .from(r)
                .fetch()
                .into(EngineSelectData.class);
    }

    @Override
    public List<EngineSelectData> getClassificationSocietyData() {
        ClassificationSocieties cs = ClassificationSocieties.CLASSIFICATION_SOCIETIES;

        return context
                .select(cs.CLASSIFICATION_SOCIETY_ID.as("id"), cs.NAME.as("value"))
                .from(cs)
                .fetch()
                .into(EngineSelectData.class);
    }

    @Override
    public List<EngineSelectData> getFlangeTypesData() {
        Flanges f = Flanges.FLANGES;

        return context
                .select(f.FLANGE_ID.as("id"), f.TYPE.as("value"))
                .from(f)
                .fetch()
                .into(EngineSelectData.class);
    }
}
