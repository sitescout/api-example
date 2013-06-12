package com.sitescout.ui.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sitescout.dsp.api.model.dto.campaign.SiteRuleDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsListDTO;

import javax.inject.Named;
import java.io.Serializable;

/**
 * Responsible for creating arrays that will be read by flot when creating graphs.
 * This class will map site statistics to ArrayNode.
 */
@Named
public class CampaignDetailsSiteGraph implements Serializable {

    public String getData(StatsListDTO<EntityStatsDTO<SiteRuleDTO>, StatsDTO> campaignSiteData) {
        if (campaignSiteData == null) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        ArrayNode clicks = root.putArray("clicks");
        ArrayNode bids = root.putArray("bids");
        ArrayNode wins = root.putArray("wins");
        ArrayNode domains = root.putArray("domains");
        ArrayNode type = root.putArray("type");
        int counter = 0;

        for (EntityStatsDTO<SiteRuleDTO> entities : campaignSiteData.getResults()) {

            ArrayNode clicksPoint = clicks.addArray();
            clicksPoint.add(counter);
            clicksPoint.add(entities.getStats().getClicks());

            ArrayNode bidsPoint = bids.addArray();
            bidsPoint.add(counter);
            bidsPoint.add(entities.getStats().getImpressionsBid());

            ArrayNode winsPoint = wins.addArray();
            winsPoint.add(counter);
            winsPoint.add(entities.getStats().getImpressionsWon());

            ArrayNode domainsPoint = domains.addArray();
            domainsPoint.add(counter);
            if (entities.getEntity().getDomain().length() > 15) {
                domainsPoint.add(entities.getEntity().getDomain().substring(0, 15) + "...");
            } else {
                domainsPoint.add(entities.getEntity().getDomain());
            }
            counter++;
        }
        type.add("site");
        return root.toString();
    }
}
