package com.sitescout.dsp.api.model.dto.stats;

import com.sitescout.dsp.api.model.dto.AudienceDTO;

public class AudienceEntityStatsDTO extends GenericEntityStatsDTO<AudienceDTO, AudienceStatsDTO> {
    public AudienceEntityStatsDTO(AudienceDTO entity, AudienceStatsDTO stats) {
        super(entity, stats);
    }
}
