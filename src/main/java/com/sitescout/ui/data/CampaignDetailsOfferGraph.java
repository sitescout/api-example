package com.sitescout.ui.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sitescout.dsp.api.model.dto.OfferDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsListDTO;

import javax.inject.Named;
import java.io.Serializable;

/**
 * Responsible for creating arrays that will be read by flot when creating graphs.
 * This class will map offer statistics to ArrayNode.
 */
@Named
public class CampaignDetailsOfferGraph implements Serializable {

    public String getData(StatsListDTO<EntityStatsDTO<OfferDTO>, StatsDTO> campaignSiteData) {
        if (campaignSiteData == null) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        ArrayNode clicks = root.putArray("clicks");
        ArrayNode bids = root.putArray("bids");
        ArrayNode wins = root.putArray("wins");
        ArrayNode type = root.putArray("type");


        for (EntityStatsDTO<OfferDTO> entities : campaignSiteData.getResults()) {

            ArrayNode clicksPoint = clicks.addArray();
            clicksPoint.add(entities.getEntity().getOfferId());
            clicksPoint.add(entities.getStats().getClicks());

            ArrayNode bidsPoint = bids.addArray();
            bidsPoint.add(entities.getEntity().getOfferId());
            bidsPoint.add(entities.getStats().getImpressionsBid());

            ArrayNode winsPoint = wins.addArray();
            winsPoint.add(entities.getEntity().getOfferId());
            winsPoint.add(entities.getStats().getImpressionsWon());

        }
        type.add("offer");
        return root.toString();
    }
}
